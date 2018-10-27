    import java.util.*;
    public class DoctorSchedule {

        public class Appt implements Comparable < Appt > {

            private double start;
            private double end;

            Appt(double start, double end) {
                this.start = start;
                this.end = end;
            }

            public int compareTo(Appt other) {
                if ((this.start - other.start) > 0) {
                    return 1;
                } else if ((this.start - other.start) < 0) {
                    return -1;
                } else return 0;
            }

            public double getEnd() {
                return this.end;
            }

            public double getStart() {
                return this.start;
            }

            public void setStart(double start) {
                this.start = start;
            }

            public void setEnd(double end) {
                this.end = end;
            }

            public void printAppt(){
                System.out.println("Checking out appointment starting at " + this.start + " and ending at " + this.end);
            }
        }

        public class endComparator implements Comparator < Appt > {
            @Override
            public int compare(Appt firstAppt, Appt secondAppt) {
                double difference = firstAppt.getEnd() - secondAppt.getEnd();
                if (difference > 0) return 1;
                if (difference < 0) return -1;
                else return 0;
            }
        }

        public void startNoConflicts(){
            System.out.println("Today is a new Day, with no conflicts!");

            List<Appt> appointments = new ArrayList<Appt>();
            appointments.add(new Appt(10, 12.30));
            appointments.add(new Appt(12.30, 14.30));
            appointments.add(new Appt(14.30, 16.30));
            appointments.add(new Appt(16.30, 18.30));

            startSchedule(appointments);
        }

        public void startEightConflicts(){
            System.out.println("Today is a new day, with a lot of conflict");

            List<Appt> appointments = new ArrayList<Appt>();
            appointments.add(new Appt(10, 12.30));
            appointments.add(new Appt(12.30, 14.30));
            appointments.add(new Appt(14.30, 16.30));
            appointments.add(new Appt(16.30, 18.30));
            appointments.add(new Appt(10.30, 14.30));
            appointments.add(new Appt(0,20));
            appointments.add(new Appt(11,12.30));
            appointments.add(new Appt(10.11, 12.30));
            appointments.add(new Appt(10.12, 12.30));
            appointments.add(new Appt(10.13, 12.30));
            appointments.add(new Appt(10.14, 12.30));

            startSchedule(appointments);
        }


        public void startFourConflicts(){
            System.out.println("Today is a new Day, with some conflict");
            List<Appt> appointments = new ArrayList<Appt>();
            appointments.add(new Appt(10, 12.30));
            appointments.add(new Appt(12.30, 14.30));
            appointments.add(new Appt(14.30, 16.30));
            appointments.add(new Appt(16.30, 18.30));
            appointments.add(new Appt(10.30, 14.30));
            appointments.add(new Appt(0,20));
            appointments.add(new Appt(11,12.30));

            startSchedule(appointments);
        }

        public void startSchedule(List<Appt> appointments) {
            Collections.sort(appointments);

            PriorityQueue<Appt> appointmentQueue = new PriorityQueue<Appt>(appointments.size(), new endComparator());

            for (Appt newAppt: appointments) {
                 newAppt.printAppt();

                if (appointmentQueue.peek() == null || appointmentQueue.peek().getEnd() > newAppt.getStart()) {
                    if(appointmentQueue.peek() != null) System.out.println("The Doctors are busy, Schedule another one!");

                    appointmentQueue.add(newAppt);
                } else {
                    Appt earliestAvail = appointmentQueue.remove();

                    earliestAvail.setEnd(newAppt.getEnd());

                    appointmentQueue.add(earliestAvail);
                }
            }

            System.out.println("We need " + appointmentQueue.size() + " doctors for today\n");
        }

        public static void main(String[] args) {
            DoctorSchedule ds = new DoctorSchedule();
            ds.startFourConflicts();
            ds.startEightConflicts();
            ds.startNoConflicts();
        }
    }