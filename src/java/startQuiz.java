import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="startQuiz")
public class startQuiz {
    public static String tmpName,tmpEmail;
    private String name;
    private String email;
    private int score;
    private String resp;
    public startQuiz(){
        try{
            getQuiz.getreq();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }

    public String getResp() {
        return resp;
    }
    public String start(){
        return "quiz";
    }
    public String leaderboard(){
        return "leaderboard";
    }

    public void setName(String name) {
        tmpName=name;
        this.name = name;
    }

    public void setEmail(String email) {
        tmpEmail=email;
        this.email = email;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getScore() {
        return score;
    }
    
}
