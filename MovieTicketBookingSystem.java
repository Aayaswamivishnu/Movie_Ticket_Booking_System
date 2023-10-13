import java.util.*;

class Movie {
    private String title;
    private int availableSeats;
    private double ticketPrice;
    private List<Reservation> reservations;

    public Movie(String title, int availableSeats, double ticketPrice) {
        this.title = title;
        this.availableSeats = availableSeats;
        this.ticketPrice = ticketPrice;
        this.reservations = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void bookSeats(int numSeats, String userName) {
        if (numSeats <= availableSeats) {
            availableSeats -= numSeats;
            Reservation reservation = new Reservation(userName, numSeats, ticketPrice);
            reservations.add(reservation);
            System.out.println("Tickets booked successfully!");
        } else {
            System.out.println("Not enough seats available.");
        }
    }

    public void showMovieDetails() {
        System.out.println("Movie: " + title);
        System.out.println("Available Seats: " + availableSeats);
        System.out.println("Ticket Price: $" + ticketPrice);
    }
}

class Reservation {
    private String userName;
    private int numSeats;
    private double totalPrice;

    public Reservation(String userName, int numSeats, double ticketPrice) {
        this.userName = userName;
        this.numSeats = numSeats;
        this.totalPrice = numSeats * ticketPrice;
    }

    public String getUserName() {
        return userName;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

class Theater {
    private List<Movie> movies;

    public Theater() {
        movies = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public Movie getMovieByTitle(String title) {
        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }
}

class User {
    private String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}

public class MovieTicketBookingSystem {
    public static void main(String[] args) {
        Theater theater = new Theater();
        theater.addMovie(new Movie("Movie A", 100, 10.0));
        theater.addMovie(new Movie("Movie B", 150, 12.0));

        Scanner scanner = new Scanner(System.in);
        List<User> users = new ArrayList<>();

        while (true) {
            System.out.println("\nAvailable Movies:");
            List<Movie> movies = theater.getMovies();
            for (int i = 0; i < movies.size(); i++) {
                System.out.println((i + 1) + ". " + movies.get(i).getTitle());
            }

            System.out.print("Enter the movie number (0 to exit): ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                break;
            }

            if (choice >= 1 && choice <= movies.size()) {
                Movie selectedMovie = movies.get(choice - 1);
                selectedMovie.showMovieDetails();
                System.out.print("Enter your username: ");
                String userName = scanner.next();

                User user = findUserByUsername(users, userName);

                if (user == null) {
                    user = new User(userName);
                    users.add(user);
                }

                System.out.print("Enter the number of seats to book: ");
                int numSeats = scanner.nextInt();
                selectedMovie.bookSeats(numSeats, userName);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Thank you for using the Movie Ticket Booking System!");
    }

    private static User findUserByUsername(List<User> users, String username) {
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
