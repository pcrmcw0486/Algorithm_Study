package solvedac.level6.gold23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q13334
 * @문제이름 : 철로
 * @난이도 : GoldII
 * @date : 2022-03-09 오후 9:06
 * @author : pcrmcw0486
 * @문제이해
 * 집과사무실을 통근하는 n명의 사람
 * 사무실은 수평선 상의 서로 다른 점 위치
 * 철로의 길이는 d로 정해져잇음
 * A의 집 혹은 사무실의 위치가 B의 집 혹은 사무실의 위치와 같을 수 있다.
 * 집과 사무실의 위치 모두 철로 선분에 포함되는 사람들의 수가 최대가 되도록 철로 선분을 정하자.
 * 집과 사무실의 위치 모두 철로 선분에 포함되도록 하는
 * @알고리즘
 * 이분탐색? 정렬.
 *
 * @접근방법
 * 가장 Naiive하게 접근하자면
 * 모든 점들에 대해서 L만큼 가보는 방법이 있다.
 * 길이가 d인 모든선부 L에 대해 집과 사무실의 위치가 모두 L에 포함되는 사람들의 최대수
 * L을 옮겨가면서 확인하면 된다. 가장 naiive하게 하면
 * 시작지점 기준으로 만약 시작지점이
 * 그럼 N만큼 확인하는거니까 N은 100,000 에 NlogN
 * 위치는 -100,000,000 ~ 100,000,000 까지.
 * 어떤 시작지점을 고르면 끝 지점은 나온다.
 * 끝 지점보다 시작지점이 낮은 마지막 친구를 찾으면 개수가 나온다.logN만큼 확인한다
 * 한번 확인시 logN에 NlogN만큼 확인 근데, 같은 시작지점이면 굳이 확인할 필요 없다.
 * 필요한건 다음과 같다. 시작지점 sorting 끝지점 sorting.
 * 시작지점으로부터
*/
public class Q13334 {
    static int N;
    static int[] start, end;
    public static void main(String[] args) throws IOException {
        //TODO
    }

    public
    static class Point{
        int idx, value;
        public Point(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }
}
