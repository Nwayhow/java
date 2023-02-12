Varchar / num /13965540430

        Welcome / "您好 / 请问有什么可以帮您?"

        Branch / "投诉" ;Process/ complainProc ;Speak/"您的意见是我们改进工作的动力"/"请问您还有什么补充";Branch /"退钱" ;Process/ returnMoneyProc ;Speak/"已经退款成功"

        Branch /"账单";Process /billProc ;Speak / "您的本月账单是"  /num/"元"/"感谢您的来电"/"再见";

        Exit/5;Speak /"请问你对客服的服务满意吗"

        Unknown/"你好"/"请再说一遍"/"或致电人工客服的号码为:"/num;Process/ feedbackProc

        Remark/顾客是上帝