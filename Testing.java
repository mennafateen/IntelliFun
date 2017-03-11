import educationalelectronicgaming.DataBaseManager;
import educationalelectronicgaming.Game;
import educationalelectronicgaming.Question;
import educationalelectronicgaming.Student;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Testing {
    
    public Testing() {
    }

    @DataProvider(name = "login data")
    public Object[][] logindata(){
        // correct login data
        Student student1 = new Student();
        student1.Name = "salma";
        student1.Password = "123";
        // wrong login data
        Student student2 = new Student();
        student2.Name = "salma";
        student2.Password = "111";
        
        return new Object[][] {
            {student1, true},
            {student2, false}
        };
    }       
    @Test(dataProvider = "login data")
    public static void loginTesting(Student s, boolean check) throws IOException {
        DataBaseManager db = new DataBaseManager();
        Assert.assertEquals(check, db.RetriveStudent(s));
    }
    
    @DataProvider(name = "signup data")
    public Object[][] signupdata(){
        // student already exists
        Student student1 = new Student();
        student1.Name = "salma";
        student1.Password = "111";
        
        // correct signup data
        Student student2 = new Student();
        student2.Name = "bayan";
        student2.Password = "123456";
        student2.Address = "cairo";
        student2.Age = 20;
        student2.DateOfBirth = "30/12/1996";
        student2.Email = "bayan@gmail.com";
        student2.Mobile = "6561487854";
        student2.Gender = "female";
              
        return new Object[][] {
            {student1, false},
            {student2, true}
        };
    }       
    @Test(dataProvider = "signup data")
    public static void signupTesting(Student s, boolean check) throws IOException {
        DataBaseManager db = new DataBaseManager();
        Assert.assertEquals(check, db.AddStudent(s));
    }
    
    // add game .... changed in the original code from void to boolean
     @DataProvider(name = "add game")
    public Object[][] addgame() {
        // correct data
        Question q1 = new Question();
        q1.Question = "Is the sky blue?";
        q1.CorrectAnswer = "T";
        q1.Choices[0] = "T";
        q1.Choices[1] = "F";
        q1.Choices[2] = "";
        q1.Choices[3] = "";
        
        Question q2 = new Question();
        q1.Question = "Is the earth flat?";
        q2.CorrectAnswer = "F";
        q2.Choices[0] = "T";
        q2.Choices[1] = "F";
        q2.Choices[2] = "";
        q2.Choices[3] = "";
        
        Game game1 = new Game();
        game1.Name = "test2";
        game1.Type = "TF";
        game1.NumberOfQuestions = 2;
        game1.QuestionsArray.add(q1);
        game1.QuestionsArray.add(q2);
       
        // game already exists
        Game game2 = new Game();
        game2.Name = "test";
        
        return new Object[][] {
            {game1, true},
            {game2, false}
        };
    }   
    @Test(dataProvider = "add game")
    public static void addGameTesting(Game g, boolean check) throws IOException {
        DataBaseManager db = new DataBaseManager();
        Assert.assertEquals(check, db.AddGame(g));
    }
    
    // play game
    @DataProvider(name = "retreive game")
    public Object[][] retrievegame() {
        // correct data
        Game game1 = new Game();
        game1.Name = "test";
        // wrong data
        Game game2 = new Game();
        game2.Name = "test3";
        return new Object[][] {
            {game1, "test"},
            {game2, null}
        };
    }
    @Test(dataProvider = "retreive game")
    public static void retreiveGameTesting(Game g, String check) throws IOException {
        DataBaseManager db = new DataBaseManager();
        Assert.assertEquals(check, db.RetriveGame(g).Name);
    }
    
    @DataProvider(name = "play game")
    public Object[][] playGame() {
        // correct data
        Game game1 = new Game();
        game1.Name = "test";

        // wrong data
        Game game2 = new Game();
        game2.Name = "wrong";

        return new Object[][] {
            {game1, true},
            {game2, false}
        };
    }
//    @Test(dataProvider = "play game")
//    public static void playGameTesting(Achievement a, boolean check) throws IOException {
//        Student s = new Student();
//        Assert.assertEquals(check, s.ViewAchievment(s.Name, s.Password));
//    }
//    
    
      
}
