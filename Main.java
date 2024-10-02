import java.util.ArrayList;
import java.util.List;

// Define a class for Service
class Service {
    String name;
    double price;       // Price of the service
    double deliveryTime; // Delivery time in days
    double rating;      // Service rating out of 5

    Service(String name, double price, double deliveryTime, double rating) {
        this.name = name;
        this.price = price;
        this.deliveryTime = deliveryTime;
        this.rating = rating;
    }
}

// Define Fuzzy Logic Functions
class FuzzyLogic {
    
    // Fuzzify price (low, medium, high)
    public String fuzzifyPrice(double price) {
        if (price < 50) return "low";
        else if (price >= 50 && price <= 100) return "medium";
        else return "high";
    }

    // Fuzzify delivery time (fast, moderate, slow)
    public String fuzzifyDeliveryTime(double deliveryTime) {
        if (deliveryTime <= 3) return "fast";
        else if (deliveryTime > 3 && deliveryTime <= 7) return "moderate";
        else return "slow";
    }

    // Fuzzify rating (poor, average, excellent)
    public String fuzzifyRating(double rating) {
        if (rating <= 2) return "poor";
        else if (rating > 2 && rating <= 4) return "average";
        else return "excellent";
    }
    
    // Generate fuzzy rule-based recommendation score (simple logic for demo purposes)
    public double generateFuzzyScore(String price, String deliveryTime, String rating) {
        double score = 0;
        if (price.equals("low")) score += 0.4;
        if (deliveryTime.equals("fast")) score += 0.3;
        if (rating.equals("excellent")) score += 0.3;
        
        return score; // Score between 0 and 1
    }
}

// Recommendation System
class FuzzyPreferenceRecommenderSystem {

    public Service recommendService(List<Service> services, FuzzyLogic fuzzyLogic) {
        Service bestService = null;
        double bestScore = 0;

        // Iterate through all services
        for (Service service : services) {
            // Fuzzify attributes
            String priceFuzzy = fuzzyLogic.fuzzifyPrice(service.price);
            String deliveryTimeFuzzy = fuzzyLogic.fuzzifyDeliveryTime(service.deliveryTime);
            String ratingFuzzy = fuzzyLogic.fuzzifyRating(service.rating);
            
            // Generate fuzzy score based on rules
            double score = fuzzyLogic.generateFuzzyScore(priceFuzzy, deliveryTimeFuzzy, ratingFuzzy);
            
            // Compare score to find the best service
            if (score > bestScore) {
                bestService = service;
                bestScore = score;
            }
        }
        
        return bestService;
    }
}

public class Main {

    public static void main(String args[]) {
        // Create a list of available services
        List<Service> services = new ArrayList<>();
        services.add(new Service("Service A", 30, 2, 4.5));
        services.add(new Service("Service B", 75, 5, 3.8));
        services.add(new Service("Service C", 120, 7, 4.9));
        
        // Initialize Fuzzy Logic and Recommender System
        FuzzyLogic fuzzyLogic = new FuzzyLogic();
        FuzzyPreferenceRecommenderSystem recommender = new FuzzyPreferenceRecommenderSystem();
        
        // Recommend the best service based on fuzzy preferences
        Service bestService = recommender.recommendService(services, fuzzyLogic);
        
        // Output the best recommended service
        if (bestService != null) {
            System.out.println("Recommended Service: " + bestService.name);
            System.out.println("Price: $" + bestService.price);
            System.out.println("Delivery Time: " + bestService.deliveryTime + " days");
            System.out.println("Rating: " + bestService.rating + " stars");
        } else {
            System.out.println("No suitable service found.");
        }
    }
}
