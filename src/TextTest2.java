import java.nio.charset.StandardCharsets;

public class TextTest2 {

    // 1줄에 10 ~ 15Byte 수용가능
    // 1줄에 글자 5 ~ 10자 수용가능

    // 한글 5자 길이를 기준 (15Byte)
    // 영어 10자 (10Byte)
    // 한글, 영어 섞이면 - 5 ~ 9자
    // 안abcdefghi -> 안abcdefgh => 1, 8 => 3, 8 = 11
    // 안녕abcdefgh -> 안녕abcdef => 2, 6 => 6, 6 = 12
    // 안녕하abcdefgh -> 안녕하abcd => 3, 4 => 9, 4 = 13
    // 안녕하세abcdefgh -> 안녕하세ab => 4, 2 => 12, 2 = 14

    // 한글 18자 길이를 기준 (54Byte)
    // 영어 36자 (36Byte)
    // 한글, 영어가 섞이면

    // 영어 2 == 한글 1
    // 한글 2
    // 영어 1
    public static int getLineLength(String content, int startAt) {
        // 1페이지당 시작 글자 index
        content = content.substring(startAt);

        // length 초기화
        double length = 0;
        int loops = 0;
        char[] charArray = content.toCharArray();

        // content가 없으면 종료
        if (charArray.length < 1)
            return -1;

        for (char c : charArray) {
            if (c != '\n')
                // System.out.print(c);
                if (c >= 0x00 && c <= 0x7F) {
                    // 1. 아스키코드 안에 포함되면 (영어, 숫자, 특수문자)
                    length = length + 0.5;
                    // System.out.println("아스키 length : " + length);
                } else {
                    // 2. 아스키코드 안에 포함안되면 (한글)
                    length = length + 1;
                    // System.out.println("한글 length : " + length);
                }

            // 필요한 length 개수
            loops++;

            if (5 <= length && length <= 10) {
                break;
            }
            // \n 이 나오면 줄바꿈(fot문 종료)
            if (c == '\n')
                break;
        }
        System.out.println();

        int totalLength = loops;
        return totalLength;
    }

    // 한줄에 한글 6자
    // 총 2줄 필요하다.
    public static void main(String[] args) {

        // 1. 책 내용
        String content = "안녕\n하세요.반갑습니다.^^제이름은 ;;입니다. 만나서 반가워요오!!";

        // 제일 최초의 글자 index
        int startAt = 0;

        // 1page
        for (int i = 1; i <= 2; i++) {
            // length = [5, 7]
            int length = getLineLength(content, startAt); // [0, startAt+lenght = 5, startAt+length = 12]
            startAt = startAt + length;
            System.out.println("★ 1page length = " + length);

            if (length == -1)
                break;
        }

        // 2page startAt
        // System.out.println("★ 2page startAt : " + startAt);
        for (int i = 1; i <= 2; i++) {
            // length = [5, 7]
            int length = getLineLength(content, startAt); // [0, startAt+lenght = 5, startAt+length = 12]
            startAt = startAt + length;
            System.out.println("★ 2page length = " + length);

            if (length == -1)
                break;
        }

        // 3page startAt
        for (int i = 1; i <= 2; i++) {
            // length = [5, 7]
            int length = getLineLength(content, startAt); // [0, startAt+lenght = 5, startAt+length = 12]
            startAt = startAt + length;
            System.out.println("★ 3page length = " + length);

            if (length == -1)
                break;
        }
    }
}
