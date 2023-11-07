import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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

    // 한글 14자 길이를 기준 (52Byte)
    // 영어 28자 (28Byte)
    // 한글, 영어가 섞이면 1줄에 14 ~ 28자 수용 가능

    // 영어 2 == 한글 1
    public static int getLineLength(String content, int startAt) {
        // 1페이지당 시작 글자 index
        // 해당 index 이후 다음 글자
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

            // 1줄에 사용된 텍스트 갯수
            loops++;

            if (14 <= length && length <= 28) {
                break;
            }

            // \n 이 나오면 줄바꿈(fot문 종료)
            if (c == '\n')
                break;
        }

        int totalLength = loops;
        return totalLength;
    }

    public static List<String> splitTextIntoPages(String content) {
        int pages = 0;
        int startAt = 0;
        List<String> pageList = new ArrayList<>();

        while (startAt < content.length()) {
            StringBuilder pageText = new StringBuilder();
            int length = 0;
            for (int i = 1; i <= 18; i++) {
                length = getLineLength(content, startAt);
                startAt = startAt + length;

                if (length == -1)
                    break;

                pageText.append(content, startAt - length, startAt); // 1줄 당 content의 시작 텍스트 위치와 끝 위치
                pageText.append("\n"); // 페이지를 구분할 \n
            }

            pageList.add(pageText.toString());
            // StringBuilder는 문자열을 가변적으로 다루기 위해 설계된 클래스로, 문자열을 동적으로 추가, 수정, 삭제할 수 있습니다. 따라서
            // StringBuilder는 문자열을 효율적으로 다루기 위한 용도로 사용됩니다.

            // StringBuilder 객체에 저장된 문자열은 StringBuilder가 제공하는 toString() 메서드를 호출하여 String으로
            // 변환할 수 있습니다. 이렇게 변환된 String은 불변이며 수정이 불가능한 상태가 됩니다.

            pages++;

            if (length == -1)
                break;
        }

        System.out.println("총 page 수 : " + pages);
        // System.out.println("pageList : " + pageList);

        return pageList;
    }

    // 한줄에 한글 6자
    // 총 2줄 필요하다.
    public static void main(String[] args) {

        // 1. 책 내용
        String content = "3. 인간 행동 잉면에는 언제나 왜 또는 목표가 있다. 인간의 모든 활동에 목적이나 이유가 있는 것이다. 따라서 자신이 어떤 목적이나 목표를 선택하는 지 명확하게 의식해야 한다. 그러면 방법은 저절로 나타나기 시작한다. 행동은 목적이나 목표를 따라가게 마련이기 때문이다.\r\n"
                + //
                ": 집착의 법칙과 비슷한 부분이 있다. 집착(대상)의 목적(이유)를 설정하고 목표(방법)을 세우고 달성해야 한다. \r\n" + //
                "지금 오늘의 내가 생각한 목적과 목표가 분명 작년과는 조금 다르다. 방향과 대상은 같지만 이유가 달라졌고, 방법에도 약간의 변화가 생겼다. 여느때 처럼 다이어리에 목표로만 남겨두고 행동하지 않았다면? 행동했기 때문에 목적과 목표가 수정되었고, \r\n"
                + //
                "의식했기 때문에 행동이 바뀌고 있다고 생각한다.";

        // page마다 split 된 contentList
        List<String> pageList = splitTextIntoPages(content);

        for (String string : pageList) {
            System.out.println("page");
            System.out.println(string);
        }

        // int length = getLineLength(content, 0);
        // System.out.println(length);
    }

}
