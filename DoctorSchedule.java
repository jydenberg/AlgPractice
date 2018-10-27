// Java code to illustrate size()
import java.util.*;
public class DoctorSchedule {

    public class Appt implements Comparable < Appt > {

        public double start;
        public double end;

        Appt(double start, double end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Appt other) {
            if ((this.start - other.start) > 1) {
                return 1;
            } else if ((this.start - other.start) < 1) {
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

    public void DoctorSchedule() {};

    public void startSchedule() {
        ArrayList < Appt > appointments = new ArrayList < Appt > ();
        Appt a1 = new Appt(10.30, 12.30);
        Appt a2 = new Appt(12.30, 14.30);
        Appt a3 = new Appt(14.30, 16.30);
        Appt a4 = new Appt(16.30, 18.30);
        Appt a5 = new Appt(10.30, 14.30);
        Appt a6 = new Appt(0,20);
        Appt a7 = new Appt(10.30,12.30);
        appointments.add(a1);
        appointments.add(a2);
        appointments.add(a3);
        appointments.add(a4);
        appointments.add(a5);
        appointments.add(a6);
        appointments.add(a7);
        appointments.add(a7);
        appointments.add(a7);
        appointments.add(a7);
        appointments.add(a7);

        Collections.sort(appointments);

        PriorityQueue < Appt > appointmentQueue = new PriorityQueue < Appt > (appointments.size(), new endComparator());

        for (Appt newAppt: appointments) {
            if (appointmentQueue.peek() == null || appointmentQueue.peek().getEnd() > newAppt.getStart()) {
                appointmentQueue.add(newAppt);
            } else {
                Appt earliestAvail = appointmentQueue.remove();
                earliestAvail.setEnd(newAppt.getEnd());
                appointmentQueue.add(earliestAvail);
            }
        }

        System.out.println("The max amount of doctors needed is " + appointmentQueue.size());
    }

    public static void main(String[] args) {
        DoctorSchedule ds = new DoctorSchedule();
        ds.startSchedule();
    }
}