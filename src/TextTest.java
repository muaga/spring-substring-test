import java.nio.charset.StandardCharsets;

public class TextTest {

    // 한글(+띄어쓰기) : 영어(+숫자,특수문자) = 2 : 1
    public static double getSize(String content) {
        double size = 0;
        char[] charArray = content.toCharArray();
        for (char c : charArray) {
            if (c >= 0x00 && c <= 0x7F) {
                size = size + 0.5;
            } else {
                size = size + 1;
            }
        }
        return size;
    }

    // 페이지당 글자 size
    // index = 0부터 시작
    // page = 0부터 시작
    // substring(포함, 불포함)
    public static String getPaging(int page, int size, String content) {
        // 만약, page 당 size가 5씩 가진다면
        int start = page * size; // 0 * 5 = 0 || 1 * 5 = 5
        int end = (page + 1) * size; // 1 * 5 = 5 || 2 * 5 = 10
        // 첫 번째-> index 0,1,2,3,4
        // 두 번째-> index 5,6,7,8,9
        content = content.substring(start, end);
        return content;
    }

    public static String getPagingAndSize(int page, double wantsize, String content) {
        double size = 0;
        char[] charArray = content.toCharArray();
        int loops = 0;
        for (char c : charArray) {
            if (c >= 0x00 && c <= 0x7F) {
                size = size + 0.5;
            } else {
                size = size + 1;
            }
            loops++;

            if (size >= wantsize) {
                break;
            }
        }

        int start = (int) (page * loops); // 0 * 5 = 0 || 1 * 5 = 5
        int end = (int) ((page + 1) * loops); // 1 * 5 = 5 || 2 * 5 = 10
        // 첫 번째-> index 0,1,2,3,4
        // 두 번째-> index 5,6,7,8,9
        content = content.substring(start, end);

        return content;
    }

    public static void main(String[] args) {

        // 1. 책 내용
        String content = "3. 인간 행동 잉면에는 언제나 왜 또는 목표가 있다. 인간의 모든 활동에 목적이나 이유가 있는 것이다. 따라서 자신이 어떤 목적이나 목표를 선택하는 지 명확하게 의식해야 한다. 그러면 방법은 저절로 나타나기 시작한다. 행동은 목적이나 목표를 따라가게 마련이기 때문이다.\r\n"
                + //
                ": 집착의 법칙과 비슷한 부분이 있다. 집착(대상)의 목적(이유)를 설정하고 목표(방법)을 세우고 달성해야 한다. \r\n" + //
                "지금 오늘의 내가 생각한 목적과 목표가 분명 작년과는 조금 다르다. 방향과 대상은 같지만 이유가 달라졌고, 방법에도 약간의 변화가 생겼다. 여느때 처럼 다이어리에 목표로만 남겨두고 행동하지 않았다면? 행동했기 때문에 목적과 목표가 수정되었고, \r\n"
                + //
                "의식했기 때문에 행동이 바뀌고 있다고 생각한다.";

        String word = "abcd안녕하세요";
        // 2. 책 글자의 size
        double size = getSize(word);
        System.out.println(size);

        // 3. 페이지 당 글자 개수
        String result1 = getPaging(0, 3, content);
        System.out.println("5글자씩 : " + result1);
        String result2 = getPagingAndSize(0, 200, content);
        System.out.println("5글자씩 : " + result2);
    }
}
