import java.io.*;
import java.util.*;


public class Minmin {

    public static void main (String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of reducers: ");
        int rnum = Integer.parseInt(scanner.next());
        System.out.print("Enter the number of servers: ");
        int snum = Integer.parseInt(scanner.next());

         /* Matrix to contain the completion time of each task on each machine */
        int[][] matrix = {{599877,600068,600050,600342,599986,600426,599798,600324,599350,598924,600276,599365,600750,599087,600326,601051},
                {599719,600229,599963,600519,600062,600357,599616,600597,598879,598865,600588,599021,600757,599110,600475,601243},
                {599813,600048,600066,600570,600313,600165,599565,600193,599466,599344,600525,599166,600610,598732,600186,601238},
                {600049,599775,599801,600299,600475,600111,599607,600093,599439,599191,600396,599593,600701,598965,600139,601366},
                {599910,599790,600419,600106,600107,600340,599574,600341,599407,598979,600737,599329,600462,599166,600390,600943},
                {599793,600254,600066,600391,600199,600094,599578,600126,599059,599283,600605,599361,600854,599108,600437,600792},
                {599946,600150,599676,600462,600269,600146,599537,600307,599455,599211,600167,599092,600978,599273,600346,600985},
                {599925,599958,599855,600363,600294,600576,599509,600379,599553,599146,600357,598898,600485,599103,600459,601140},
                {599889,599842,599577,600399,600014,600507,599717,600613,599300,598822,600623,599546,600890,598811,600155,601295},
                {599817,599865,600007,600200,600305,600513,599623,600145,599384,599243,600392,599054,600862,599235,600415,600940},
                {599951,599853,600203,600108,600369,600178,599306,600361,599046,599276,600368,599293,600355,599391,600616,601326},
                {600252,599714,600151,600147,600277,600439,599735,599925,599123,599302,600961,598901,600497,599066,600618,600892},
                {600120,600244,599926,600456,599839,600382,599546,600283,599306,599123,600780,599081,600676,598874,600438,600926},
                {599965,599959,600112,600227,600266,600354,599696,600055,599176,599321,600523,599226,600622,599315,600325,600858},
                {599651,600641,599922,600274,600286,600088,599476,600375,599069,598943,600513,599415,600693,599321,600288,601045},
                {599754,600099,600219,600535,599883,600188,599673,600345,599356,599213,600423,599331,600652,598757,600321,601251}};

        /* Flag whether a reducer has been scheduled */
        boolean[] isRemoved = new boolean[rnum];
        boolean[] isUsed = new boolean[snum];
        int tasksRemoved = 0;
        int[] assignment = new int[rnum];
        int[] cost = new int[rnum];

        do{
            int minTime=Integer.MAX_VALUE;
            int machine=-1;
            int taskNo=-1;

            /* Find the task with the earliest completion time and the corresponding machine */
            for (int i = 0; i < rnum; i++){
                if (isRemoved[i]) continue;
                for (int j = 0;j < snum; j++){
                    if (isUsed[j]) continue;
                    if (matrix[i][j] < minTime){
                        minTime = matrix[i][j];
                        machine = j;
                        taskNo = i;
                    }
                }
            }

            /* Record the assignment of reducer i to server j */
            assignment[taskNo] = machine;
            cost[taskNo] = minTime;
            /* Mark this task as removed */
            tasksRemoved++;
            isRemoved[taskNo]=true;
            isUsed[machine] = true;

            /* Update Matrix for other remaining tasks */
            for(int i=0;i < rnum;i++){
                if (isRemoved[i]) continue;
                else{
                    matrix[i][machine] += matrix[taskNo][machine];
                }
            }

        } while (tasksRemoved != rnum);

        for (int element : assignment) {
            System.out.print(element + ",");
        }

        System.out.print("\n");
        int sum = 0;
        int max = 0;
        for (int element : cost) {
            sum += element;
            if (element > max) max = element;
            System.out.print(element + ",");
        }
        System.out.print("\n");
        System.out.print("Max: " + max + "; Sum: " + sum + "\n");

    }

}