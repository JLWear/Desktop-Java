package model;

public class Training {

        public Training(Integer total_load, Float monotony, Integer constraint, Integer fitness, Float acwr){
            this.total_load = total_load;
            this.monotony = monotony;
            this.constraint = constraint;
            this.fitness = fitness;
            this.acwr = acwr;
        }

        private int total_load;
        private float monotony;
        private int constraint;
        private int fitness;
        private float acwr;

        public Integer getTotalLoad() {
            return total_load;
        }

        public void setTotalLoad(Integer total_load) {
            this.total_load = total_load;
        }

        public Float getMonotony() {
            return monotony;
        }

        public void setMonotony(Float monotony) {
            this.monotony = monotony;
        }

        public Integer getConstraint() {
            return constraint;
        }

        public void setConstraint(Integer constraint) {
            this.constraint = constraint;
        }

        public Integer getFitness() {
            return fitness;
        }

        public void setFitness(Integer fitness) {
            this.fitness = fitness;
        }

        public Float getAcwr() {
            return acwr;
        }

        public void setAcwr(Float acwr) {
            this.acwr = acwr;
        }

}
