public class NotFoundException extends  Exception{
    String message;
    public NotFoundException(){
        super();
    }
    public NotFoundException(String message){
        this.message=message;
    }
    public String getmessage(){
        return message;
    }

}
