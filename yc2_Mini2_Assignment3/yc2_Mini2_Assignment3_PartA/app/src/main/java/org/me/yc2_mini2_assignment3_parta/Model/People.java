package org.me.yc2_mini2_assignment3_parta.Model;

/**
 * Created by caoyi on 16/3/22.
 */
public abstract class People {

        private  int SID;
        private  int[] scores=new int[5];

        public People(int sID, int[] scores) {
            super();
            this.SID = sID;
            this.scores = scores;
        }


        public int getSID() {
            return SID;
        }


        public  void setSID(int SID) {

            this.SID = SID;
        }


        public  int[] getScores() {
            return scores;
        }


        public  void setScores(int[] scores) {
            this.scores = scores;
        }
}
