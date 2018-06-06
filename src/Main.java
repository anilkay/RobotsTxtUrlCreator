public class Main {
    public static void main(String[]args){
        if(args.length<=0){
            System.err.println("İnvalid  Argument");
            return;
        }
        String webSiteName=args[0];
        Creator creator=null;
        if(args.length==1) {
             creator = new Creator(args[0], "*");
        }
        if(args.length>=2){
            String starReplacement=args[1];
            creator = new Creator(args[0],starReplacement);
        }

        String solution=creator.CreateUrl();
        System.out.println(solution);
    }
}
