import java.util.*;

class Train {
    private String trainNo;
    private Map<String, Route> routes; // map route for better search of route
    private ArrayList<_SLcoach> _sLcoachs;
    private ArrayList<_3Acoach> _3Acoachs;
    private ArrayList<_2Acoach> _2Acoachs;
    private ArrayList<_1Acoach> _1Acoachs;

    Train(String tn) {
        this.trainNo = tn;
        this.routes = new HashMap<>();
        this._sLcoachs = new ArrayList<>();
        this._3Acoachs = new ArrayList<>();
        this._2Acoachs = new ArrayList<>();
        this._1Acoachs = new ArrayList<>();
    }

    public String getTrainNo() {
        return trainNo;
    }

    void addSL(_SLcoach sl) {
        this._sLcoachs.add(sl);
    }

    void add3a(_3Acoach _3a) {
        this._3Acoachs.add(_3a);
    }

    void add2a(_2Acoach _2a) {
        this._2Acoachs.add(_2a);
    }

    void add1a(_1Acoach _1a) {
        this._1Acoachs.add(_1a);
    }

    void addRoutes(String n, Route r) {
        this.routes.put(n, r);
    }

    public Map<String, Route> getRoutes() {
        return routes;
    }

    public ArrayList<_SLcoach> get_sLcoachs() {
        return _sLcoachs;
    }

    public ArrayList<_3Acoach> get_3Acoachs() {
        return _3Acoachs;
    }

    public ArrayList<_2Acoach> get_2Acoachs() {
        return _2Acoachs;
    }

    public ArrayList<_1Acoach> get_1Acoachs() {
        return _1Acoachs;
    }
}

class Route {
    private String Rname;
    private int dis;

    Route(String name, int dis) {
        this.Rname = name;
        this.dis = dis;
    }

    public int getDis() {
        return dis;
    }

    public void setDis(int dis) {
        this.dis = dis;
    }

    public void setRname(String rname) {
        Rname = rname;
    }

    public String getRname() {
        return Rname;
    }
}

class Coach {
    private int coachNo;
    private int noOfSeats;
    private ArrayList<Seat> seats;

    Coach(int cn, int ns) {
        this.coachNo = cn;
        this.noOfSeats = ns;
        this.seats = new ArrayList<>();
        makeSeats(seats, ns);
    }

    void makeSeats(ArrayList<Seat> seats, int noOfSeats) {
        for (int i = 1; i <= noOfSeats; i++) {
            Seat seat = new Seat(i, "NotReserved");
            seats.add(seat);
        }
    }

    public int getCoachNo() {
        return coachNo;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }
}

class _SLcoach extends Coach {

    _SLcoach(int cn, int ns) {
        super(cn, ns);
    }
}

class _3Acoach extends Coach {

    _3Acoach(int cn, int ns) {
        super(cn, ns);
    }

}

class _2Acoach extends Coach {

    _2Acoach(int cn, int ns) {
        super(cn, ns);
    }

}

class _1Acoach extends Coach {
    _1Acoach(int cn, int ns) {
        super(cn, ns);
    }

}

class Seat {
    private int seatNo;
    private String reservedStatus;
    private Set<String> dates;

    Seat(int seatNo, String res) {
        this.seatNo = seatNo;
        this.reservedStatus = res;
        this.dates = new HashSet<>();
    }

    public int getSeatNo() {
        return seatNo;
    }

    public String getReservedStatus() {
        return reservedStatus;
    }

    public void setReservedStatus(String reservedStatus) {
        this.reservedStatus = reservedStatus;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public Set<String> getDates() {
        return dates;
    }

    public void setDates(String str) {
        this.dates.add(str);
    }
}

class Ticket {
    private Train train;
    private int prn;
    private String trainNo;
    private String src;
    private String des;
    private String date;
    private int fare;
    private Set<String> seats;

    public Ticket(Train t, int prn, String src, String des, String date, int fare, Set<String> seatsList) {
        this.train = t;
        this.prn = prn;
        this.trainNo = t.getTrainNo();
        this.src = src;
        this.des = des;
        this.date = date;
        this.fare = fare;
        this.seats = seatsList;
    }

    public Set<String> getseats() {
        return seats;
    }

    public String getDate() {
        return date;
    }

    public String getDes() {
        return des;
    }

    public int getFare() {
        return fare;
    }

    public int getPrn() {
        return prn;
    }

    public String getSrc() {
        return src;
    }

    public Train getTrain() {
        return train;
    }

    public String getTrainNo() {
        return trainNo;
    }
}

public class TrainReservationSystem2 {

    ArrayList<Train> allTrains;
    Map<String, Train> getTrainByNumber = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    Map<String, ArrayList<Train>> dateTrains;
    Map<Integer, Ticket> Mapedtickets;
    ArrayList<Ticket> tickets;

    int PRN = 100000001;

    TrainReservationSystem2() {
        this.allTrains = new ArrayList<>();
        this.dateTrains = new HashMap<>();
        this.Mapedtickets = new HashMap<>();
        this.tickets = new ArrayList<>();
    }

    void AddTrainDetail(int noOfTrain) {
        for (int i = 0; i < noOfTrain; i++) {
            System.out.println();
            System.out.println("Enter Train Details of Train " + (i + 1) + " in Format of");
            System.out.println(
                    "TrainNumber StartingFrom-Distance EndinAt-Distance for Example 17726 Rajkot-0 Mumbai-750");
            String[] trainDetail = sc.nextLine().split(" ");

            // create train
            Train train = new Train(trainDetail[0]);

            // Add routes of the train
            for (int j = 1; j < trainDetail.length; j++) {
                String routeDetail[] = trainDetail[j].split("-");
                Route route = new Route(routeDetail[0], Integer.parseInt(routeDetail[1]));
                train.addRoutes(routeDetail[0], route);
            }

            // Add coachs of the train
            System.out.println("Enter Coach Details of Train " + (i + 1));
            String[] coachDetail = sc.nextLine().split(" ");
            for (int k = 1; k < coachDetail.length; k++) {
                String[] eachCoachDetail = coachDetail[k].split("-");

                char cage = eachCoachDetail[0].charAt(0);
                int coachNo = Integer.parseInt(eachCoachDetail[0].substring(1));
                int noOfSeats = Integer.parseInt(eachCoachDetail[1]);

                if (cage == 'S') {
                    Coach c = new _SLcoach(coachNo, noOfSeats);
                    train.addSL((_SLcoach) c);
                } else if (cage == 'B') {
                    Coach c = new _3Acoach(coachNo, noOfSeats);
                    train.add3a((_3Acoach) c);

                } else if (cage == 'A') {
                    Coach c = new _2Acoach(coachNo, noOfSeats);
                    train.add2a((_2Acoach) c);

                } else if (cage == 'H') {
                    Coach c = new _1Acoach(coachNo, noOfSeats);
                    train.add1a((_1Acoach) c);
                }
            }
            allTrains.add(train);
            getTrainByNumber.put(trainDetail[0], train);
        }
    }

    int CalculateCharge(int dis, int NoOfPerson, String cate) {
        if (cate.equals("SL"))
            return 1 * dis * NoOfPerson;
        else if (cate.equals("3A"))
            return 2 * dis * NoOfPerson;
        else if (cate.equals("3A"))
            return 3 * dis * NoOfPerson;
        else
            return 4 * dis * NoOfPerson;
    }

    void BookingRequst() {

        System.out.println();
        System.out.println("Enter Booking Request if Format of \n src des date coachType noOfSeats");
        String bookingReq[] = sc.nextLine().split(" ");
        String src = bookingReq[0];
        String des = bookingReq[1];
        String date = bookingReq[2];
        String cate = bookingReq[3]; // SL,3A,2A,1A
        int noOfSeatToBook = Integer.parseInt(bookingReq[4]);
        List<String> availableTrains = new ArrayList<>();

        ArrayList<Train> trains = allTrains;
        boolean isTrain = false;
        for (Train t : trains) {
            if (t.getRoutes().containsKey(src) && t.getRoutes().containsKey(des)
                    && IsSeatAvailable(t, cate, noOfSeatToBook, date)) {
                isTrain = true;
                availableTrains.add(t.getTrainNo());
            }
        }
        if (isTrain) {
            System.out.println("Available Trains:- " + availableTrains.toString());
            System.out.println("Enter in Which Train You want to Book TICKET!");
            String trainNum = sc.nextLine();
            if (!availableTrains.contains(trainNum)) {
                System.out.println("Invalid Train!!");
            } else {
                Train train = getTrainByNumber.get(trainNum);
                Booking(train, date, src, des, cate, noOfSeatToBook);
            }
        }
        if (!isTrain)
            System.out.println("\n No Trains Available");
    }

    private void Booking(Train t, String date, String src, String des, String cate,
            int noOfSeatToBook) {

        int distance = t.getRoutes().get(des).getDis() - t.getRoutes().get(src).getDis();
        boolean seatsAvi = IsSeatAvailable(t, cate, noOfSeatToBook, date);
        Set<String> seatsList = new HashSet<>();
        if (seatsAvi) {
            BookSeat(t, noOfSeatToBook, cate, date, seatsList);
            int fare = CalculateCharge(distance, noOfSeatToBook, cate);
            Ticket ticket = new Ticket(t, PRN, src, des, date, fare, seatsList);
            System.out.println();
            System.out.println("Ticket Booked!!!");
            System.out.println("PRN Number:- " + ticket.getPrn());
            System.out.println("From:- " + ticket.getSrc());
            System.out.println("To:- " + ticket.getDes());
            System.out.println("Date:- " + ticket.getDate());
            System.out.println("Coaches:- " + seatsList.toString());
            System.out.println("Fare Amount:- " + fare);

            Mapedtickets.put(PRN, ticket);
            tickets.add(ticket);
            PRN++;
        } else
            System.out.println("\n No Seats Available!");
    }

    void BookSeat(Train t, int noOfSeatToBook, String cate, String date, Set<String> seatsList) {

        if (cate.equals("SL")) {
            ArrayList<_SLcoach> sl = t.get_sLcoachs();
            for (_SLcoach sll : sl) {
                for (Seat seat : sll.getSeats()) {
                    if (!seat.getDates().contains(date) && noOfSeatToBook > 0) {
                        seat.setDates(date);
                        seatsList.add("S" + sll.getCoachNo() + "-" + seat.getSeatNo());
                        noOfSeatToBook--;
                    }
                }
            }
        }

        else if (cate.equals("3A")) {
            ArrayList<_3Acoach> sl = t.get_3Acoachs();
            for (_3Acoach sll : sl) {
                for (Seat seat : sll.getSeats()) {
                    if (!seat.getDates().contains(date) && noOfSeatToBook > 0) {
                        seat.setDates(date);
                        seatsList.add("B" + sll.getCoachNo() + "-" + seat.getSeatNo());
                        noOfSeatToBook--;
                    }
                }
            }

        } else if (cate.equals("2A")) {
            ArrayList<_2Acoach> sl = t.get_2Acoachs();
            for (_2Acoach sll : sl) {
                for (Seat seat : sll.getSeats()) {
                    if (!seat.getDates().contains(date) && noOfSeatToBook > 0) {
                        seat.setDates(date);
                        seatsList.add("A" + sll.getCoachNo() + "-" + seat.getSeatNo());
                        noOfSeatToBook--;
                    }
                }
            }

        } else if (cate.equals("1A")) {
            ArrayList<_1Acoach> sl = t.get_1Acoachs();
            for (_1Acoach sll : sl) {
                for (Seat seat : sll.getSeats()) {
                    if (!seat.getDates().contains(date) && noOfSeatToBook > 0) {
                        seat.setDates(date);
                        seatsList.add("H" + sll.getCoachNo() + "-" + seat.getSeatNo());
                        noOfSeatToBook--;
                    }
                }
            }
        }

    }

    private boolean IsSeatAvailable(Train t, String cate, int noOfSeatToBook, String date) {
        int AvailableSeatInRequestedCoach = 0;
        if (cate.equals("SL")) {
            for (_SLcoach sl : t.get_sLcoachs()) {
                for (Seat seat : sl.getSeats()) {
                    if (!seat.getDates().contains(date))
                        AvailableSeatInRequestedCoach++;
                }
            }
        } else if (cate.equals("3A")) {
            for (_3Acoach sl : t.get_3Acoachs()) {
                for (Seat seat : sl.getSeats()) {
                    if (!seat.getDates().contains(date))
                        AvailableSeatInRequestedCoach++;
                }
            }
        }
        if (cate.equals("2A")) {
            for (_2Acoach sl : t.get_2Acoachs()) {
                for (Seat seat : sl.getSeats()) {
                    if (!seat.getDates().contains(date))
                        AvailableSeatInRequestedCoach++;
                }
            }
        }
        if (cate.equals("1A")) {
            for (_1Acoach sl : t.get_1Acoachs()) {
                for (Seat seat : sl.getSeats()) {
                    if (!seat.getDates().contains(date))
                        AvailableSeatInRequestedCoach++;
                }
            }
        }
        return AvailableSeatInRequestedCoach >= noOfSeatToBook;
    }

    void inputReq() {
        while (true) {
            System.out.println();
            System.out.println("1. Book Ticket");
            System.out.println("2. Retrive Ticket Details using PRN");
            System.out.println("3. Generate Report");
            System.out.println("4. Stop");

            int s = Integer.parseInt(sc.nextLine());
            if (s == 1)
                BookingRequst();
            else if (s == 2)
                RetriveTicketData();
            else if (s == 3)
                GenerateReport();
            else {
                break;
            }
        }
    }

    private void GenerateReport() {
        System.out.println("PNR,  DATE,  TRAIN,  FROM,  TO,  FARE,  SEATS");
        for (Ticket t : tickets) {
            System.out.println(t.getPrn() + ", " + t.getDate() + ", " + t.getTrainNo() + ", " + t.getSrc() + ", "
                    + t.getDes() + ", " + t.getFare() + ", " + t.getseats().toString());
        }
    }

    private void RetriveTicketData() {
        System.out.println("Enter PRN number:- ");
        int prn = Integer.parseInt(sc.nextLine());

        if (Mapedtickets.containsKey(prn)) {
            Ticket ticket = Mapedtickets.get(prn);
            System.out.println("Train Number:- " + ticket.getTrainNo());
            System.out.println("From:- " + ticket.getSrc());
            System.out.println("To:- " + ticket.getDes());
            System.out.println("Date:- " + ticket.getDate());
            System.out.println("Fare Amount:- " + ticket.getFare());
            System.out.println("Seats:- " + ticket.getseats().toString());
        } else {
            System.out.println("Invalid PRN!!");
        }
    }

    public static void main(String[] args) {

        TrainReservationSystem2 tbs = new TrainReservationSystem2();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter no of Train:-");
        int noOfTrain = Integer.parseInt(sc.nextLine());

        // Add Trains Detail
        tbs.AddTrainDetail(noOfTrain);

        // take Inputs
        tbs.inputReq();

    }
}
