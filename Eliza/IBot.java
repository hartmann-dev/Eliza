package Eliza;

import Eliza.Message.Message;
import java.util.ArrayList;

public interface IBot {
    
    public String prompt();
    public String answer(String request);
    public boolean isRunning();
    public String hello();
    public void stop();
    public ArrayList<Message> history();
}
