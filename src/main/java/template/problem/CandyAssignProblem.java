package template.problem;

import template.math.DigitUtils;
import template.utils.SequenceUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * This problem is about on a circle, there are c points enumerated as 0, 1, ...,c - 1.
 * <br>
 * There are some candis on some locations, xi means there are xi candis on point x.
 * <br>
 * You can deliver candy between adjacent points, (0 and c - 1 is adjacent), deliver each candy one unit cost 1.
 * <br>
 * You have to ensure location i has yi candies, while x_0 + x_1 + ... + x_{c_1} = y_0 + y_1 + ... + y_{c_1}.
 */
public class CandyAssignProblem {
    private int c;
    List<Candy> addedCandies;
    Candy[] candies;
    int candieCnt;
    long minimumCost;


    public CandyAssignProblem(int c, int exp) {
        this.c = c;
        addedCandies = new ArrayList<>(exp);
    }

    public void requestOn(int i, int x, int y) {
        Candy candy = new Candy();
        candy.location = i;
        candy.x = x;
        candy.y = y;
        addedCandies.add(candy);
    }

    public void assignCandyOn(int i, int x) {
        requestOn(i, x, 0);
    }

    public void requireCandyOn(int i, int y) {
        requestOn(i, 0, y);
    }

    public void solve() {
        if (addedCandies.isEmpty()) {
            addedCandies.add(new Candy());
        }
        candies = addedCandies.toArray(new Candy[0]);
        Arrays.sort(candies, Candy.sortByA);
        candieCnt = 0;
        for (int i = 1; i < candies.length; i++) {
            if (candies[i].location == candies[candieCnt].location) {
                candies[candieCnt].x += candies[i].x;
                candies[candieCnt].y += candies[i].y;
            } else {
                candieCnt++;
                candies[candieCnt] = candies[i];
            }
        }
        candieCnt++;

        for (int i = 0; i < candieCnt - 1; i++) {
            candies[i].w = candies[i + 1].location - candies[i].location;
        }
        candies[candieCnt - 1].w = c + candies[0].location - candies[candieCnt - 1].location;

        for (int i = 1; i < candieCnt; i++) {
            candies[i].a = candies[i - 1].a + candies[i].x - candies[i].y;
        }

        Candy[] sortedByA = Arrays.copyOf(candies, candieCnt);
        Arrays.sort(sortedByA, (a, b) -> Long.compare(a.a, b.a));
        long prefix = 0;
        long half = DigitUtils.ceilDiv(c, 2);
        for (int i = 0; i < candieCnt; i++) {
            prefix += sortedByA[i].w;
            if (prefix >= half) {
                candies[0].a = -sortedByA[i].a;
                break;
            }
        }

        for (int i = 1; i < candieCnt; i++) {
            candies[i].a += candies[0].a;
        }

        for (int i = 0; i < candieCnt; i++) {
            minimumCost += Math.abs(candies[i].a) * candies[i].w;
        }
    }

    public long minimumCost() {
        return minimumCost;
    }


    Candy tmp = new Candy();

    /**
     * How many candies are delivered from i to i + 1, the returned value could be negative.
     */
    public long deliverBetween(int i) {
        tmp.location = i;
        int index = SequenceUtils.lowerBound(candies, tmp, 0, candieCnt - 1, Candy.sortByA);
        if (index < 0) {
            index = candieCnt - 1;
        }
        return candies[index].a;
    }

    static class Candy {
        int location;
        int x;
        int y;

        long a;
        int w;

        static Comparator<Candy> sortByA = (a, b) -> Integer.compare(a.location, b.location);
    }
}
