import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Idiom {
    public String Saying;
    public String Author;
}

public class Main {
    public static void main(String[] args) {
        new Level8().run();
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
    @Override
    void run() {
        System.out.println("==명언 앱==");
        while (true) {
            System.out.print("명령) ");
            String response = in.nextLine();
            if (response.equals("종료")) return;

            else if (response.equals("등록")) registration();

            else if (response.equals("목록")) show_Idioms();

            else if (response.equals("삭제")) delete_Idiom();

            else if (response.equals("수정")) modify_Idiom();

            else exception();
        }
    }

    void exception() {
        System.out.println("해당 명령어는 존재하지 않습니다. 다시 입력해 주세요.");
    }

    void registration() {
        Idiom buffer = new Idiom();

        System.out.print("명언: ");
        buffer.Saying = in.nextLine();

        System.out.print("작가: ");
        buffer.Author = in.nextLine();

        List.add(buffer);

        IdiomNo++;

        System.out.printf("%d번 명언이 등록되었습니다.\n", IdiomNo);
    }

    void show_Idioms() {
        System.out.println("번호 / 작가 / 명언\n----------------------");
        for (int i = 1; i <= IdiomNo; i++) {
            Idiom buffer = List.get(IdiomNo - i);
            System.out.printf("%d / %s / %s\n", IdiomNo - i + 1, buffer.Author, buffer.Saying);
        }
    }

    void delete_Idiom() {
        exception();
    }

    void modify_Idiom() {
        exception();
    }
}

class Level6 extends Level5 { //7단계도 겸사겸사 구현함.
    @Override
    void delete_Idiom() {
        int id;
        if (IdiomNo != 0) {
            while (true) {
                System.out.print("삭제할 명언의 번호: ");
                id = in.nextInt();
                in.nextLine();
                if (id > 0 && id <= IdiomNo) {
                    List.remove(id - 1);
                    break;
                } else if (id == 0) {
                    return;
                } else {
                    System.out.printf("%d번 명언은 존재하지 않습니다.\n1과 %d 사이의 번호를 골라 주세요.(취소는 0번)\n", id, IdiomNo);
                }
            }
            IdiomNo--;
            System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
        } else System.out.println("삭제할 명언이 없습니다.");
    }
}

class Level8 extends Level6 {
    @Override
    void modify_Idiom() {
        int id;
        if (IdiomNo != 0) {
            while (true) {
                System.out.print("수정할 명언의 번호: ");
                id = in.nextInt();
                in.nextLine();
                if (id > 0 && id <= IdiomNo) {
                    System.out.printf("명언(기존) : %s\n명언: ", List.get(id - 1).Saying);
                    List.get(id - 1).Saying = in.nextLine();
                    System.out.printf("작가(기존) : %s\n작가: ", List.get(id - 1).Author);
                    List.get(id - 1).Author = in.nextLine();
                    break;
                } else if (id == 0) {
                    break;
                } else {
                    System.out.printf("%d번 명언은 존재하지 않습니다.\n1과 %d 사이의 번호를 골라 주세요.(취소는 0번)\n", id, IdiomNo);
                }
            }
        } else System.out.println("수정할 명언이 없습니다.");
    }
}