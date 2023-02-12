import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

class parser{
    Boolean flag=false;//文法导入是否成功
    ArrayList<String> branch =new ArrayList<String>();//记录每一个分支的处理手段
    int numBranch=0;//分支数
    ArrayList<String> Exit =new ArrayList<String>();//记录离开时的处理方式
    int numExit=0;//结束数
    ArrayList<String> Unknown =new ArrayList<String>();//记录未知字符的处理方式
    int numUnknown=0;//未知情况处理方式数
    String[][]Varchar=new String[1000][1000];//记录变量名和其实际意义
    String []Process=new String[1000];//记录函数名
    String []Branch=new String[1000];//记录分支的关键字
    ArrayList<String> Run =new ArrayList<String>();//装入函数操作
    int numVarchar=0;//记录变量数
    ArrayList<String> Welcome =new ArrayList<String>();//转入欢迎操作
    int numWelcome=0;//记录欢迎数
    int numProcess=0;//记录函数数量
    ArrayList<String> Key = new ArrayList<String>(Arrays.asList("Welcome", "Branch", "Exit","Remark","Speak","Unknown","Process","Run","Varchar","LM10WCC"));
    //关键字列表
    parser() throws IOException {


    }
    int ParseFile(String fileName) throws IOException {
        clear();
        if(getFileLineNum(fileName)>1000) return 10;//行数过多
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        int count=0;
        while((line = br.readLine()) != null){
            count++;
            String str=line.replace(" ","");
            // 一行一行地处理...
            String[] cut  = str.split(";");//用逗号分开每一个
            int error=ParseLine(cut);
            if(error!=0){
                clear();
                return count*100+error;//数字代表报错类型
            };
        }
        flag=true;
        return 0;
    }
    public static int getFileLineNum(String filePath) {//检查行数
        try (LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(filePath))){
            lineNumberReader.skip(Long.MAX_VALUE);
            int lineNumber = lineNumberReader.getLineNumber();
            return lineNumber + 1;//实际上是读取换行符数量 , 所以需要+1
        } catch (IOException e) {
            return -1;
        }
    }
    void clear(){
        flag=false;//文法导入是否成功
        branch.clear();
        numBranch=0;
        Exit.clear();
        numExit=0;
        Unknown.clear();
        numUnknown=0;
        Varchar=new String[1000][1000];
        Branch=new String[1000];
        Process=new String[1000];
        numVarchar=0;
        Welcome.clear();
        numProcess=0;
        numWelcome=0;

    }

    int ParseLine(String cut[]){
        String[] cutdeeper  = cut[0].split("/");//读取第一行的关键字决定分析类型
        if(cutdeeper[0].equals("Welcome")){
            return ParseWelcome(cut);
        }
        else if(cutdeeper[0].equals("Varchar")){
            return ParseVarchar(cut);
        }
        else if(cutdeeper[0].equals("Exit")){
            return ParseExit(cut);
        }
        else if(cutdeeper[0].equals("Unknown")){
            return ParseUnknown(cut);
        }
        else if(cutdeeper[0].equals("Branch")){
            return ParseBranch(cut);
        }
        else if(cutdeeper[0].equals("Remark") || cutdeeper[0].equals("")){
            return 0;
        }
        else if(cutdeeper[0].equals("Run") ){
            return ParseRun(cut);
        }

        else {
            return 1;
        }


    }
    boolean isKey(String test)//检查是不是关键字
    {
        if(Key.contains(test))  return true;
        else return false;

    }
    int analyzeVar(String test){//检查变量是否已经声明
        for(int i=0;i<numVarchar;i++){
            if(test.equals(Varchar[i][0])) return 1;//已经声明
        }
        if(isKey(test)){
            return 1;//发现关键词冲突
        }
        return -1;//未声明
    }
    Boolean judgeProcess(String test)//判断函数名是否已经被声明了
    {
        for(int i=0;i<numProcess;i++){
            if(test.equals(Process[i])){
                return true;
            }
        }
        if(isKey(test)){
            return true;//发现关键词冲突
        }
        return false;
    }
    int ParseRun(String cut[])//对Run开头的行进行分析
    {
        int len=cut.length;//计算被;切割成多少块
        for(int i=0;i<len;i++){
            String[] cutdeeper  = cut[i].split("/");//再次切割符号+.进行遍历
            for(int m=0;m<cutdeeper.length;m++){
                if(cutdeeper[m].equals("Run")) {//若为Run,长度必为2,Run+函数名
                    if (cutdeeper.length != 2 ) return 11;//返回错误
                    else {
                        Run.add(cutdeeper[m]);
                        m++;
                        if(!judgeProcess(cutdeeper[m])){//判断函数名有没有重复定义或为关键词
                            Run.add(cutdeeper[m]);
                            Process[numProcess]=cutdeeper[m];
                            numProcess++;
                        }
                        else {
                            return 17;//返回错误
                        }
                    }
                }
                else if(cutdeeper[m].equals("Process")){//函数中嵌套函数
                    if(cutdeeper.length!=2) return 3;//若为Process,长度必为2,Process+函数名
                    else {
                        Run.add(cutdeeper[m]);
                        m++;
                        if(judgeProcess(cutdeeper[m])) {
                            Run.add(cutdeeper[m]);
                        }
                        else return 11;
                    }

                }
                else if(cutdeeper[m].equals("Remark")){
                    return 0;
                }
                else if(cutdeeper[m].equals("Speak")){//回答
                    if(cutdeeper.length<2) return 18;//回答后面必须有内容
                    Run.add(cutdeeper[m]);
                    m++;
                    if(cutdeeper[m].charAt(0)=='\"'){//判断语句是不是首尾都有"
                        if(cutdeeper[m].charAt(cutdeeper[m].length()-1)=='\"'){
                            Run.add(cutdeeper[m]);
                        }
                        else return 5;
                    }
                    else{
                        if(analyzeVar(cutdeeper[m])==-1) {//变量是否已经被定义
                            ;
                            return 4;
                        }
                        else{
                            Run.add(cutdeeper[m]);
                        }
                    }


                }

                else if(m!=0){//可能是第二个参数
                    if(cutdeeper[m].charAt(0)=='\"'){//判断语句是不是首尾都有"
                        if(cutdeeper[m].charAt(cutdeeper[m].length()-1)=='\"'){
                            Run.add(cutdeeper[m]);
                        }
                        else return 5;
                    }
                    else{
                        if(analyzeVar(cutdeeper[m])==-1) {
                            ;
                            return 4;
                        }
                        else{
                            Run.add(cutdeeper[m]);
                        }
                    }
                }
                else{
                    return 2;//非法字符
                }
            }

        }
        Run.add("LM10WCC");//结束符
        return 0;
    }
    boolean judgeBranch(String test)//判断分支关键词是否被重复使用
    {
        for(int i=0;i<numBranch;i++){
            if(test.equals(Branch[i])){
                return true;
            }
        }
        return false;
    }

    int ParseBranch(String cut[])//对Branch开头的行进行分析
    {
        int len=cut.length;//看看被;分成多少段
        int Bflag=0;

        for(int i=0;i<len;i++){

            String[] cutdeeper  = cut[i].split("/");//再次切割符号/
            for(int m=0;m<cutdeeper.length;m++){
                if(i==0){
                    if(judgeBranch(cutdeeper[1])){
                        return 13;// branch中的关键词重复出现
                    }
                    else numBranch++;
                }
                if(cutdeeper[m].equals("Branch")){
                    if(Bflag==0) Bflag++;
                    else return 19;//同一句branch出现过多次
                    if(cutdeeper.length!=2) return 12;//branch格式错误
                    else {
                        branch.add(cutdeeper[m]);
                        m++;
                        branch.add(cutdeeper[m]);

                    }

                }
                else if(cutdeeper[m].equals("Remark")){
                    return 0;
                }
                else if(cutdeeper[m].equals("Process")){
                    if(cutdeeper.length!=2) return 3;//process后面只能跟着一个函数
                    else {
                        branch.add(cutdeeper[m]);
                        m++;
                        if(judgeProcess(cutdeeper[m])) {//判断process有没有被定义过
                            branch.add(cutdeeper[m]);
                        }
                        else return 11;
                    }

                }
                else if(cutdeeper[m].equals("Speak")){
                    if(cutdeeper.length<2) return 18;//speak 没有跟语句
                    branch.add(cutdeeper[m]);
                    m++;
                    if(cutdeeper[m].charAt(0)=='\"'){//判断语句是不是首尾都有"
                        if(cutdeeper[m].charAt(cutdeeper[m].length()-1)=='\"'){
                            branch.add(cutdeeper[m]);
                        }
                        else return 5;
                    }
                    else{
                        if(analyzeVar(cutdeeper[m])==-1) {

                            return 4;//变量未定义
                        }
                        else{
                            branch.add(cutdeeper[m]);
                        }
                    }


                }
                else if(m!=0){
                    if(cutdeeper[m].charAt(0)=='\"'){//判断语句是不是首尾都有"
                        if(cutdeeper[m].charAt(cutdeeper[m].length()-1)=='\"'){
                            branch.add(cutdeeper[m]);
                        }
                        else return 5;
                    }
                    else{
                        if(analyzeVar(cutdeeper[m])==-1) {//变量未定义

                            return 4;
                        }
                        else{
                            branch.add(cutdeeper[m]);
                        }
                    }
                }
                else{
                    return 2;
                }
            }

        }
        branch.add("LM10WCC");
        return 0;

    }
    int ParseWelcome(String cut[])//对welcome开头的行进行分析
    {
        if(numWelcome==0) numWelcome++;//welcome只能出现一次
        else return 15;
        int len=cut.length;
        for(int i=0;i<len;i++){
            String[] cutdeeper  = cut[i].split("/");//再次切割符号+
            for(int m=0;m<cutdeeper.length;m++){
                if(cutdeeper[m].equals("Welcome")){
                    Welcome.add(cutdeeper[m]);

                }
                else if(cutdeeper[m].equals("Process")){
                    if(cutdeeper.length!=2) return 3;//Process后不只跟着一个函数
                    else {
                        Welcome.add(cutdeeper[m]);
                        m++;
                        if(judgeProcess(cutdeeper[m])) {
                            Welcome.add(cutdeeper[m]);
                        }
                        else return 11;
                    }

                }
                else if(cutdeeper[m].equals("Remark")){
                    return 0;
                }
                else if(cutdeeper[m].equals("Speak")){//
                    if(cutdeeper.length<2) return 18;//speak 没有跟语句
                    Welcome.add(cutdeeper[m]);
                    m++;
                    if(cutdeeper[m].charAt(0)=='\"'){//判断语句是不是首尾都有"
                        if(cutdeeper[m].charAt(cutdeeper[m].length()-1)=='\"'){
                            Welcome.add(cutdeeper[m]);
                        }
                        else return 5;
                    }
                    else{
                        if(analyzeVar(cutdeeper[m])==-1) {//变量未定义
                            return 4;
                        }
                        else{
                            Welcome.add(cutdeeper[m]);
                        }
                    }


                }
                else if(m!=0){
                    if(cutdeeper[m].charAt(0)=='\"'){//判断语句是不是首尾都有"
                        if(cutdeeper[m].charAt(cutdeeper[m].length()-1)=='\"'){
                            Welcome.add(cutdeeper[m]);
                        }
                        else return 5;
                    }
                    else{
                        if(analyzeVar(cutdeeper[m])==-1) {//变量未定义
                            return 4;
                        }
                        else{
                            Welcome.add(cutdeeper[m]);
                        }
                    }
                }
                else{
                    return 2;// 关键词错误
                }
            }

        }
        Welcome.add("LM10WCC");
        return 0;
    }
    int ParseVarchar(String cut[])//对Varchar开头的行进行分析
    {
        int len=cut.length;
        for(int i=0;i<len;i++){
            String[] cutdeeper  = cut[i].split("/");//再次切割符号,
            for(int m=0;m<cutdeeper.length;m++){
                if(cutdeeper[m].equals("Varchar")){
                    if(cutdeeper.length!=3) return 6;//声明变量不规范
                    else if(analyzeVar(cutdeeper[1])==1) {
                        return 8;
                    }
                    else {
                        m++;
                        Varchar[numVarchar][0]=cutdeeper[m];
                        m++;
                        Varchar[numVarchar][1]=cutdeeper[m];
                        numVarchar++;
                    }

                }
                else if(cutdeeper[m].equals("Remark")){
                    return 0;
                }

            }

        }
        return 0;
    }
    public static boolean isNumeric1(String str)//判断是不是数字
    {
        try {
            Double.parseDouble(str);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    int ParseExit(String cut[])//对Exit开头的行进行分析
    {
        if(numExit==0) numExit++;//exit不能出现多次
        else return 16;
        int len=cut.length;
        for(int i=0;i<len;i++){
            String[] cutdeeper  = cut[i].split("/");//再次切割符号,
            for(int m=0;m<cutdeeper.length;m++){
                if(cutdeeper[m].equals("Exit")){
                    if(cutdeeper.length!=2) return 7;//exit格式错误
                    Exit.add(cutdeeper[m]);
                    m++;
                    if(!isNumeric1(cutdeeper[m])){//此处数字代表秒数,必须为整数
                        return 9;// exit跟着不是整数
                    }
                    else{
                        Exit.add(cutdeeper[m]);
                    }
                }
                else if(cutdeeper[m].equals("Remark")){
                    return 0;
                }
                else if(cutdeeper[m].equals("Process")){
                    if(cutdeeper.length!=2) return 3;// process后不只跟着一个函数
                    else {
                        Exit.add(cutdeeper[m]);
                        m++;
                        if(judgeProcess(cutdeeper[m])) {
                            Exit.add(cutdeeper[m]);
                        }
                        else return 11;// run后面的函数没有定义或格式错误
                    }
                }
                else if(cutdeeper[m].equals("Speak")){
                    if(cutdeeper.length<2) return 18;// speak 没有跟语句
                    Exit.add(cutdeeper[m]);
                    m++;
                    if(cutdeeper[m].charAt(0)=='\"'){//判断语句是不是首尾都有"
                        if(cutdeeper[m].charAt(cutdeeper[m].length()-1)=='\"'){
                            Exit.add(cutdeeper[m]);
                        }
                        else return 5;//
                    }
                    else{
                        if(analyzeVar(cutdeeper[m])==-1) {//变量未定义
                            ;
                            return 4;
                        }
                        else{
                            Exit.add(cutdeeper[m]);
                        }
                    }


                }
                else if(m!=0){
                    if(cutdeeper[m].charAt(0)=='\"'){//判断语句是不是首尾都有"
                        if(cutdeeper[m].charAt(cutdeeper[m].length()-1)=='\"'){
                            Exit.add(cutdeeper[m]);
                        }
                        else return 5;//语句没用""包围
                    }
                    else{
                        if(analyzeVar(cutdeeper[m])==-1) {//变量未定义
                            ;
                            return 4;
                        }
                        else{
                            Exit.add(cutdeeper[m]);
                        }
                    }
                }
                else{
                    return 2;
                }
            }

        }
        Exit.add("LM10WCC");
        return 0;
    }
    int ParseUnknown(String cut[])//对Unknown开头的行进行分析//即不能匹配任何branch关键词的语句
    {
        //即不能匹配任何branch关键词的语句
        if(numUnknown==0) numUnknown++;//unknown不能出现多次
        else return 14;
        int len=cut.length;
        for(int i=0;i<len;i++){
            String[] cutdeeper  = cut[i].split("/");//再次切割符号,
            for(int m=0;m<cutdeeper.length;m++){
                if(cutdeeper[m].equals("Unknown")){
                    Unknown.add(cutdeeper[m]);

                }
                else if(cutdeeper[m].equals("Process")){//process后不只跟着一个函数
                    if(cutdeeper.length!=2) return 3;
                    else {
                        Unknown.add(cutdeeper[m]);
                        m++;
                        if(judgeProcess(cutdeeper[m])) {//run后面的函数没有定义或格式错误
                            Unknown.add(cutdeeper[m]);
                        }
                        else return 11;
                    }

                }
                else if(cutdeeper[m].equals("Remark")){
                    return 0;
                }
                else if(cutdeeper[m].equals("Speak")){
                    if(cutdeeper.length<2) return 18;//speak 没有跟语句
                    Unknown.add(cutdeeper[m]);
                    m++;
                    if(cutdeeper[m].charAt(0)=='\"'){//判断语句是不是首尾都有"
                        if(cutdeeper[m].charAt(cutdeeper[m].length()-1)=='\"'){
                            Unknown.add(cutdeeper[m]);
                        }
                        else return 5;// 语句没用""包围
                    }
                    else{
                        if(analyzeVar(cutdeeper[m])==-1) {
                            ;
                            return 4;//变量未定义
                        }
                        else{
                            Unknown.add(cutdeeper[m]);
                        }
                    }


                }
                else if(m!=0){
                    if(cutdeeper[m].charAt(0)=='\"'){//判断语句是不是首尾都有"
                        if(cutdeeper[m].charAt(cutdeeper[m].length()-1)=='\"'){
                            Unknown.add(cutdeeper[m]);
                        }
                        else return 5;
                    }
                    else{
                        if(analyzeVar(cutdeeper[m])==-1) {//变量未定义

                            return 4;
                        }
                        else{
                            Unknown.add(cutdeeper[m]);
                        }
                    }
                }
                else{
                    return 2;
                }
            }

        }
        Unknown.add("LM10WCC");
        return 0;
    }
}