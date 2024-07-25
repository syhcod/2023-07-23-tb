import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Idiom {
    Idiom() {}
    Idiom(String Saying, String Author) {
        this.Saying = Saying;
        this.Author = Author;
        this.authorLength = Author.length();
    }
    public String Saying = "";
    public String Author = "";
    int authorLength = 0;
}

public class Main {
    public static void main(String[] args) {
        tests t = new tests();
        CustomLevel App = new CustomLevel();
        t.execute(App, 4);
        App.run();
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
            switch (response) {
                case "종료" -> {
                    return;
                }
                case "등록" -> registration();
                case "목록" -> show_Idioms();
                case "삭제" -> delete_Idiom();
                case "수정" -> modify_Idiom();
                default -> exception();
            }
        }
    }

    void registration() { exception(); }

    void delete_Idiom() { exception(); }

    void modify_Idiom() { exception(); }

    void show_Idioms() { exception(); }

    void exception () {
        System.out.println("해당 명령어는 존재하지 않습니다. 다시 입력해 주세요.");
    }

    protected List<Idiom> List = new ArrayList<>();
}

class Level3 extends Level2 { //4단계까지 겸사겸사 구현함.
    @Override
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

    protected int IdiomNo = 0;
}

class Level5 extends Level3 {

    void show_Idioms() {
        System.out.println("번호 / 작가\t/ 명언\n----------------------");
        for (int i = 1; i <= IdiomNo; i++) {
            Idiom buffer = List.get(IdiomNo - i);
            System.out.printf("%d\t/ %s\t/ %s\n", IdiomNo - i + 1, buffer.Author, buffer.Saying);
        }
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

class CustomLevel extends Level8 {

    @Override
    void registration() {
        Idiom buffer = new Idiom();

        System.out.print("명언: ");
        buffer.Saying = in.nextLine();

        System.out.print("작가: ");
        buffer.Author = in.nextLine();

        append_to_List(buffer);
    }

    String format_Author(String Author) {
        StringBuilder strbuilder;

        int alen = len_of_String(Author);

        if (alen < max_author_length) {
            strbuilder = new StringBuilder(Author);
            strbuilder.append(" ".repeat(max_author_length - alen));
            Author = strbuilder.toString();
        } else if (alen > max_author_length) {
            int dif = alen - max_author_length;
            for (Idiom Id : List) {
                strbuilder = new StringBuilder(Id.Author);
                strbuilder.append(" ".repeat(dif));
                Id.Author = strbuilder.toString();
            }
            max_author_length = alen;
        }

        return Author;
    }

    void append_to_List(Idiom buffer) {
        buffer.authorLength = len_of_String(buffer.Author);

        buffer.Author = format_Author(buffer.Author);

        List.add(buffer);

        IdiomNo++;

        System.out.printf("%d번 명언이 등록되었습니다.\n", IdiomNo);
    }

    @Override
    void show_Idioms() {
        System.out.print("번호 / 작가 ");
        for (int i = 0; i < max_author_length - 4; i++)
            System.out.print(" ");
        System.out.println("/ 명언\n----------------------");
        for (int i = 1; i <= IdiomNo; i++) {
            StringBuilder num = new StringBuilder(Integer.toString(IdiomNo - i + 1));
            num.append(" ".repeat(4 - num.length()));
            Idiom buffer = List.get(IdiomNo - i);
            System.out.printf("%s / %s / %s\n", num.toString(), buffer.Author, buffer.Saying);
        }
    }

    int len_of_String(String s) {
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c > ' ' && c < '~') len += 1;
            else len += 2;
        }
        return len;
    }

    void shorten_authors() {
        max_author_length = 4;
        for (Idiom i : List) {
            if (i.authorLength > max_author_length)
                max_author_length = i.authorLength;
        }
        for (Idiom i : List) {
            int checkLength = 0;
            int specificLength = 0;
            while (checkLength < max_author_length) {
                char c = i.Author.charAt(specificLength++);

                if (c >= ' ' && c <= '~') checkLength += 1;
                else checkLength += 2;
            }
            i.Author = i.Author.substring(0, specificLength);
        }
    }

    @Override
    void delete_Idiom() {
        int id;
        int authorlen = 0;
        if (IdiomNo != 0) {
            while (true) {
                id = new Request().getParamAsInt("삭제할 명언의 번호: ");
                if (id > 0 && id <= IdiomNo) {
                    authorlen = List.get(id - 1).authorLength;
                    List.remove(id - 1);
                    if (authorlen == max_author_length)
                        shorten_authors();
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

    @Override
    void modify_Idiom() {
        int id;
        int authorLength = 0;
        int originalLength = 0;

        if (IdiomNo != 0) {
            while (true) {
                id = new Request().getParamAsInt("삭제할 명언의 번호: ");
                if (id > 0 && id <= IdiomNo) {
                    originalLength = List.get(id - 1).authorLength;

                    System.out.printf("명언(기존) : %s\n명언: ", List.get(id - 1).Saying);
                    List.get(id - 1).Saying = in.nextLine();
                    System.out.printf("작가(기존) : %s\n작가: ", List.get(id - 1).Author);
                    String bufferAuthor = in.nextLine();

                    authorLength = len_of_String(bufferAuthor);
                    bufferAuthor = format_Author(bufferAuthor);

                    List.get(id - 1).Author = bufferAuthor;
                    List.get(id - 1).authorLength = authorLength;

                    if (originalLength == max_author_length)
                        shorten_authors();

                    break;
                } else if (id == 0) {
                    break;
                } else {
                    System.out.printf("%d번 명언은 존재하지 않습니다.\n1과 %d 사이의 번호를 골라 주세요.(취소는 0번)\n", id, IdiomNo);
                }
            }
        } else System.out.println("수정할 명언이 없습니다.");
    }

    protected int max_author_length = 4;
}

class Request {
    int getParamAsInt(String RequestMessage) {
        String input;
        int answer;
        while (true) {
            System.out.print(RequestMessage);
            input = scan.nextLine();
            try {
                answer = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("번호를 입력해 주세요.");
                continue;
            }
            break;
        }
        return answer;
    }
    private final Scanner scan = new Scanner(System.in);
}

class tests {
    tests() {
        testCases = new Idiom[4];
        for (int i = 0; i < 4; i++)
            testCases[i] = new Idiom();
        testCases[0].Author = "";
        testCases[0].Saying = "가는 귀가 먹는다.";
        testCases[1].Author = "나";
        testCases[1].Saying = "훌랑훌랑";
        testCases[2].Author = "엄r";
        testCases[2].Saying = "니글니글 버터플라이";
        testCases[3].Author = "가나다라";
        testCases[3].Saying = "마바사 아자차카타파하";
    }

    void execute(CustomLevel c, int k) {
        if (k > 4) k = 4;
        for (int i = 0; i < k; i++) {
            c.append_to_List(testCases[i]);
        }
    }

    public Idiom[] testCases;
}