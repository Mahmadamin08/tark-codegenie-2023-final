import java.util.*;

class Train {
    int TrainNo;
    List<Route> routes;
    List<Coach> coaches;
    List<String> RouteName;
    List<String> ClassName;
    int SL;
    int threeA;
    int twoA;
    int oneA;

    Train(int TrainNo) {
        this.TrainNo = TrainNo;
        this.routes = new ArrayList<>();
        this.coaches = new ArrayList<>();
        this.RouteName = new ArrayList<>();
        this.ClassName = new ArrayList<>();
    }

    void SetRoute(Route route) {
        this.routes.add(route);
        this.RouteName.add(route.GetRouteName());
    }

    void Calculate() {
        for (Coach c : coaches) {
            if (c.getName().charAt(0) == 'S') {
                this.SL = SL + c.GetNoOfSeat();
            } else if (c.getName().charAt(0) == 'B') {
                this.threeA = threeA + c.GetNoOfSeat();
            } else if (c.getName().charAt(0) == 'A') {
                this.twoA = twoA + c.GetNoOfSeat();
            } else if (c.getName().charAt(0) == 'H') {
                this.oneA = oneA + c.GetNoOfSeat();
            }
        }
    }

    void Setcoaches(Coach cls) {
        this.ClassName.add(cls.GetName());
        this.coaches.add(cls);
    }

    int GetTrianNo() {
        return this.TrainNo;
    }

    boolean getClass(String cla, int reqSeat) {
        for (Coach c : coaches) {
            if (cla.equals("SL")) {
                if (SL >= reqSeat) {
                    this.SL = SL - reqSeat;
                    return true;
                }
            } else if (cla.equals("3A")) {
                if (threeA >= reqSeat) {
                    this.threeA = threeA - reqSeat;
                    return true;
                }
            } else if (cla.equals("2A")) {
                if (twoA >= reqSeat) {
                    this.twoA = twoA - reqSeat;
                    return true;
                }
            } else if (cla.equals("1A")) {
                if (oneA >= reqSeat) {
                    this.oneA = oneA - reqSeat;
                    return true;
                }
            }
        }
        return false;
    }
}

class Route {
    String Name;
    Map<String, Integer> Namedis;
    int Distance;

    Route(String name, int StartingDis) {
        this.Name = name;
        this.Distance = StartingDis;
        this.Namedis = new HashMap<>();
        Namedis.put(name, StartingDis);
    }

    String GetRouteName() {
        return this.Name;
    }

    int GetDistance() {
        return this.Distance;
    }
}

class Coach {

    String className;
    int NoOfSeats;
    int AvlSeat;

    Coach(String name, int NoOfSeats) {
        this.AvlSeat = NoOfSeats;
        this.className = name;
        this.NoOfSeats = NoOfSeats;
    }

    int getAvlSeats() {
        return this.AvlSeat;
    }

    String getName() {
        return this.className;
    }

    int GetNoOfSeat() {
        return this.NoOfSeats;
    }

    String GetName() {
        return this.className;
    }
}

public class TrainReservationSystem {

    long PRN = 100000000;

    void TicketBooked(Train t, String starting, String ending, boolean Flag, String cls, int NoOfSeats, int costPerKm) {

        PRN++;
        int cost = (t.routes.get(1).GetDistance() - t.routes.get(0).GetDistance()) * NoOfSeats
                * costPerKm;
        System.out.println(PRN + " " + cost);
    }

    List<Train> trains;

    TrainReservationSystem() {
        this.trains = new ArrayList<>();
    }

    List<Train> getTrains() {
        return trains;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TrainReservationSystem trs = new TrainReservationSystem();
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            String[] arr = s.split(" ");

            Train train = new Train(Integer.parseInt(arr[0]));
            for (int k = 1; k < arr.length; k++) {
                String[] r = arr[k].split("-");
                Route route = new Route(r[0], Integer.parseInt(r[1]));
                train.SetRoute(route);
            }
            String classDetails = sc.nextLine();
            String[] ClassArr = classDetails.split(" ");

            for (int u = 1; u < ClassArr.length; u++) {
                String[] classNameAndNo = ClassArr[u].split("-");
                Coach cls = new Coach(classNameAndNo[0], Integer.parseInt(classNameAndNo[1]));
                train.Setcoaches(cls);
            }
            trs.trains.add(train);
        }

        while (true) {
            String str = sc.nextLine();
            String[] ReqTicket = str.split(" ");

            String starting = ReqTicket[0];
            String ending = ReqTicket[1];
            String date = ReqTicket[2];
            String cls = ReqTicket[3];

            int NoOfSeats = Integer.parseInt(ReqTicket[4]);
            int costPerKm = 0;

            if (cls.equals("SL"))
                costPerKm = 1;
            else if (cls.equals("3A"))
                costPerKm = 2;
            else if (cls.equals("2A"))
                costPerKm = 3;
            else if (cls.equals("1A"))
                costPerKm = 4;

            boolean Flag = false;
            for (Train t : trs.trains) {
                if (t.RouteName.contains(starting) && t.RouteName.contains(ending)) {
                    Flag = true;
                    t.Calculate();
                    if (t.getClass(cls, NoOfSeats)) {
                        trs.TicketBooked(t, starting, ending, Flag, cls, NoOfSeats, costPerKm);
                        break;
                    } else {
                        System.out.println("No Seats Available");
                    }
                }
            }

        }
    }
}
