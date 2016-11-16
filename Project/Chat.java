package Project;

import Eliza.Eliza;
import Eliza.IBot;
import Eliza.Message.Message;
import Eliza.Responder.IResponder;
import Eliza.Responder.KeywordResponder;
import Eliza.Responder.TrivialResponder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Chat {

    public static void main(String[] args) throws IOException {

        
        IResponder[] responder = new IResponder[2];
        responder[0] = new KeywordResponder(getKeywordList());
        responder[1] = new TrivialResponder(getTrivialList());
        IBot eliza = new Eliza(responder);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(eliza.hello());
        while (eliza.isRunning()) {
            System.out.print(eliza.prompt());
            String input = in.readLine();
            String answer = eliza.answer(input);
            System.out.println(answer);

        }
        System.out.println("Verlauf:");        
        for(Message ele : eliza.history()){
            System.out.println(ele);
        }
    }

    private static String[] getTrivialList(){
        return new String[]{
            "Erzählen Sie mir mehr.",
            "Aha",
            "Verstehe.",
            "Können Sie das bitte genauer erklären?",
            "Wie genau meinen Sie das?"
        };
    }
    
    private static HashMap<String, String[]> getKeywordList() {
        HashMap<String, String[]> keywordList = new HashMap<>();

        keywordList.put("gut", new String[]{
            "Warum geht es Ihnen gut?",
            "Was lässt Sie gute Laune haben?",
            "was erfreut Sie so?"
        });

        keywordList.put("bescheiden", new String[]{
            "Was bedrückt Sie? Liegt es an Java?",
            "Reden Sie es sich von der Seele!",
            "Okay das sollten wir vertiefen!"
        });
        keywordList.put("familie", new String[]{
            "Was [bedrückt,erfreut] Sie genau, wenn wir darüber sprechen?",
        });

        return keywordList;

    }
}
