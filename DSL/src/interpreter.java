import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

class interpreter{
    ArrayList<String> branch ;
    ArrayList<String>  Exit;
    ArrayList<String>  Unknown;
    ArrayList<String>  Run;
    ArrayList<String>  Welcome;
    String[][]Varchar;
    ArrayList<String> Key = new ArrayList<String>(Arrays.asList("Welcome", "Branch", "Exit","Remark","Speak","Unknown","Process","Run","Varchar","LM10WCC"));
    interpreter(parser p1){
        branch = (ArrayList<String>)p1.branch.clone();
        Exit = (ArrayList<String>)p1.Exit.clone();
        Unknown = (ArrayList<String>)p1.Unknown.clone();
        Run = (ArrayList<String>)p1.Run.clone();
        Welcome = (ArrayList<String>)p1.Welcome.clone();
        Varchar=new String[1000][1000];//拷贝变量
        for(int i = 0;i < p1.Varchar.length;i++){
            for(int j = 0;j < p1.Varchar[i].length;j++){
                Varchar[i][j]=p1.Varchar[i][j];
            }
        }
    }

    void interpreterWelcome()//分析Welcome开头语句
    {
        test.ta.append("机器人:");
        for(int i=1;i<Welcome.size();i++){
            if(Welcome.get(i).equals("Process")){
                i++;
                interpreterProcess(Welcome.get(i));

            }
            else if(Welcome.get(i).equals("Speak")){
                i++;
                i=interpreterSpeak(Welcome,i)-1;


            }
            else if(Welcome.get(i).equals("Remark") ){
                test.ta.append("\n");
                return;

            }
            else if(Welcome.get(i).equals("LM10WCC") ){
                test.ta.append("\n");
                return;

            }


        }

    }
    void interpreterUnknown()//分析Unknown开头语句
    {
        for(int i=1;i<Unknown.size();i++){
            if(Unknown.get(i).equals("Process")){
                i++;
                interpreterProcess(Unknown.get(i));

            }
            else if(Unknown.get(i).equals("Speak")){
                i++;
                i=interpreterSpeak(Unknown,i)-1;


            }
            else if(Unknown.get(i).equals("Remark") ){
                test.ta.append("\n");
                return;

            }
            else if(Unknown.get(i).equals("LM10WCC") ){
                test.ta.append("\n");
                return;

            }


        }

    }
    void interpreterWord(String word)//分析Branch开头语句
    {
        int Flag=0;
        for(int i=0;i<branch.size()-1;i++){
            if(word.contains(branch.get(i+1).replace("\"","")) && branch.get(i).equals("Branch")){
                test.ta.append("机器人:");
                Flag=1;
                i=i+2;
                if(i>=branch.size()){
                    return;
                }
                for(int n=i;n<branch.size();n++) {
                    if (branch.get(n).equals("Process")) {
                        n++;
                        interpreterProcess(branch.get(n));

                    } else if (branch.get(n).equals("Speak")) {
                        n++;
                        n = interpreterSpeak(branch, n) - 1;


                    } else if (branch.get(n).equals("Remark")) {
                        test.ta.append("\n");
                        i=n;
                        n=branch.size();

                    } else if (branch.get(n).equals("LM10WCC")) {
                        test.ta.append("\n");
                        i=n;
                        n=branch.size();
                    }

                }
            }


        }

       if(Flag==0) {
           test.ta.append("机器人:");
           interpreterUnknown();
       }

        return;
    }
    int getExittime()//返回设定的最迟回复时间
    {
        return Integer.parseInt(Exit.get(1));
    }
    void interpreterExit()//分析Exit开头语句
    {
        for(int i=2;i<Exit.size();i++){
            if(Exit.get(i).equals("Process")){
                i++;
                interpreterProcess(Exit.get(i));

            }
            else if(Exit.get(i).equals("Speak")){
                i++;
                i=interpreterSpeak(Exit,i)-1;


            }
            else if(Exit.get(i).equals("Remark") ){
                test.ta.append("\n");
                return;

            }
            else if(Exit.get(i).equals("LM10WCC") ){
                test.ta.append("\n");
                return;

            }


        }
    }
    void interpreterProcess(String name)//分析Run开头的语句
    {
        for(int i=0;i<Run.size()-1;i++){
            if(Run.get(i).equals("Run") && Run.get(i+1).equals(name)){
                i=i+2;
                if(i>=Run.size()){
                    return;
                }
                else if(Run.get(i).equals("Process")){
                    i++;
                    interpreterProcess(Run.get(i));

                }
                else if(Run.get(i).equals("Speak")){
                    i++;
                    i=interpreterSpeak(Run,i)-1;

                }
                else if(Run.get(i).equals("Remark") ){
                    return;

                }

            }


        }
    }
    int interpreterSpeak(ArrayList<String> sentence,int i)//分析Speak开头语句
    {

        int n;
        for(n=i;n<sentence.size();n++){
            if(Key.contains(sentence.get(n))) return n;
            else {
                if(sentence.get(n).charAt(0)=='\"') {
                    String str = sentence.get(n).replace("\"","");
                    test.ta.append(str);
                }
                else interpreterVar(sentence.get(n));
            }

        }
        return n;
    }
    void interpreterVar(String var)//分析Varchar开头语句
    {
        for(int i=0;i<Varchar.length;i++){
            if(var.equals(Varchar[i][0])){
                test.ta.append(Varchar[i][1]);
            }
        }
        return;
    }


}
