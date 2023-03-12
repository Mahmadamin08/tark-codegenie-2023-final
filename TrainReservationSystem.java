import java.util.*;

class Train {
    int TrainNo;
    List<Route> routes;
    List<Classes> classes;
    List<String> RouteName;
    List<String> ClassName;

    Train(int TrainNo) {
        this.TrainNo = TrainNo;
        this.routes = new ArrayList<>();
        this.classes = new ArrayList<>();
        this.RouteName = new ArrayList<>();
        this.ClassName = new ArrayList<>();
    }

    void SetRoute(Route route) {
        this.routes.add(route);
        this.RouteName.add(route.GetRouteName());
    }

    void SetClasses(Classes cls) {
        this.ClassName.add(cls.GetName());
        this.classes.add(cls);
    }

    int GetTrianNo() {
        return this.TrainNo;
    }

    Classes getClass(String cla) {
        for (Classes c : classes) {
            if (c.getName().equals(cla))
                return c;
        }
        return new Classes("", 0);
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

class Classes {

    String className;
    int NoOfSeats;
    Map<String, Integer> AvlSeat;

    Classes(String name, int NoOfSeats) {
        this.AvlSeat = new HashMap<>();
        AvlSeat.put(name, NoOfSeats);
        this.className = name;
        this.NoOfSeats = NoOfSeats;
    }

    int getAvlSeats(String className) {
        return AvlSeat.get(className);
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

    void TicketBooked(Train t)
    {
        System.out.println((PRN+1)+ " "+ t. );
    }

    // boolean CheckAvailablity()
    // {

    // }

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

            Train trn = new Train(Integer.parseInt(ClassArr[0]));

            for (int u = 1; u < ClassArr.length; u++) {
                String[] classNameAndNo = ClassArr[u].split("-");
                Classes cls = new Classes(classNameAndNo[0], Integer.parseInt(classNameAndNo[1]));
                train.SetClasses(cls);
            }
        }

        while (true) {
            String str = sc.nextLine();
            String[] ReqTicket = str.split(" ");

            String starting = ReqTicket[0];
            String ending = ReqTicket[1];
            String date = ReqTicket[2];
            String cls = ReqTicket[3];
            int NoOfSeats = Integer.parseInt(ReqTicket[4]);

            boolean Flag = false;
            for (Train t : trs.trains) {
                if (t.RouteName.contains(starting) && t.RouteName.contains(ending)) {
                    Flag = true;
                    if (t.ClassName.contains(cls) && t.getClass(cls).getAvlSeats(cls) > NoOfSeats) {

                        // System.out.println(TicketBooked(t));
                        // int cost = t.routes.get()
                        // System.out.println(starting + " " + ending + " " + date + " " + cls + " " +
                        // NoOfSeats);
                        // break;
                    }
                }
            }
        }
    }
}