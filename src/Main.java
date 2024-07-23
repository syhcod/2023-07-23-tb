import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Idiom {
    public String Saying;
    public String Author;
}

public class Main {
    public static void main(String[] args) {
        new Level3().run();
    }
}

class Level1 {
    void run() {
        System.out.println("==명언 앱==");
        while (true) {
            System.out.print("명령) ");
            String response = in.nextLine();
            if (response.equals("종료")) return;
            else System.out.println("해당 명령어는 존재하지 않습니다. 다시 입력해 주세요.");
        }
    }

    protected final Scanner in = new Scanner(System.in);
}

class Level2 extends Level1 {
    @Override
    void run() {
        System.out.println("==명언 앱==");
        while (true) {
            System.out.print("명령) ");
            String response = in.nextLine();
            if (response.equals("종료")) return;
            else if (response.equals("등록")) {
                Idiom buffer = new Idiom();

                System.out.print("명언: ");
                buffer.Saying = in.nextLine();

                System.out.print("작가: ");
                buffer.Author = in.nextLine();

                List.add(buffer);
            } else System.out.println("해당 명령어는 존재하지 않습니다. 다시 입력해 주세요.");
        }
    }

    protected List<Idiom> List = new ArrayList<>();
}

class Level3 extends Level2 { //4단계까지 겸사겸사 구현함.
    @Override
    void run() {
        System.out.println("==명언 앱==");
        while (true) {
            System.out.print("명령) ");
            String response = in.nextLine();
            if (response.equals("종료")) return;
            else if (response.equals("등록")) {
                Idiom buffer = new Idiom();

                System.out.print("명언: ");
                buffer.Saying = in.nextLine();

                System.out.print("작가: ");
                buffer.Author = in.nextLine();

                List.add(buffer);

                IdiomNo++;

                System.out.printf("%d번 명언이 등록되었습니다.\n", IdiomNo);
            } else System.out.println("해당 명령어는 존재하지 않습니다. 다시 입력해 주세요.");
        }
    }

    protected int IdiomNo = 0;
}

class Level5 extends Level3 {

}