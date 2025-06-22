import java.util.Arrays;
import java.util.Comparator;

class Product implements Comparable<Product> {
    private String productId;
    private String productName;
    private String category;

    public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    @Override
    public int compareTo(Product other) {
        return this.productId.compareTo(other.productId);
    }
}

public class ECommerceSearch {
    private Product[] productsArray; // For linear search
    private Product[] productsSortedById;
    private Product[] productsSortedByName;
    private Product[] productsSortedByCategory;

    public ECommerceSearch(Product[] products) {
        this.productsArray = Arrays.copyOf(products, products.length);
        
        // Create sorted copies for binary search
        this.productsSortedById = Arrays.copyOf(products, products.length);
        Arrays.sort(productsSortedById, Comparator.comparing(Product::getProductId));
        
        this.productsSortedByName = Arrays.copyOf(products, products.length);
        Arrays.sort(productsSortedByName, Comparator.comparing(Product::getProductName));
        
        this.productsSortedByCategory = Arrays.copyOf(products, products.length);
        Arrays.sort(productsSortedByCategory, Comparator.comparing(Product::getCategory));
    }

    // Linear search that can search by any field
    public Product linearSearch(String searchValue, String field) {
        for (Product product : productsArray) {
            switch (field.toLowerCase()) {
                case "productid":
                    if (product.getProductId().equalsIgnoreCase(searchValue)) {
                        return product;
                    }
                    break;
                case "productname":
                    if (product.getProductName().equalsIgnoreCase(searchValue)) {
                        return product;
                    }
                    break;
                case "category":
                    if (product.getCategory().equalsIgnoreCase(searchValue)) {
                        return product;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid search field: " + field);
            }
        }
        return null;
    }

    // Binary search that can search by any field
    public Product binarySearch(String searchValue, String field) {
        Product[] searchArray;
        Comparator<Product> comparator;
        
        switch (field.toLowerCase()) {
            case "productid":
                searchArray = productsSortedById;
                comparator = Comparator.comparing(Product::getProductId);
                break;
            case "productname":
                searchArray = productsSortedByName;
                comparator = Comparator.comparing(Product::getProductName);
                break;
            case "category":
                searchArray = productsSortedByCategory;
                comparator = Comparator.comparing(Product::getCategory);
                break;
            default:
                throw new IllegalArgumentException("Invalid search field: " + field);
        }

        int left = 0;
        int right = searchArray.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Product midProduct = searchArray[mid];
            
            int comparison;
            switch (field.toLowerCase()) {
                case "productid":
                    comparison = midProduct.getProductId().compareToIgnoreCase(searchValue);
                    break;
                case "productname":
                    comparison = midProduct.getProductName().compareToIgnoreCase(searchValue);
                    break;
                case "category":
                    comparison = midProduct.getCategory().compareToIgnoreCase(searchValue);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid search field: " + field);
            }

            if (comparison == 0) {
                return midProduct;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Create some sample products
        Product[] products = {
            new Product("P100", "Laptop", "Electronics"),
            new Product("P203", "Smartphone", "Electronics"),
            new Product("P045", "Desk Chair", "Furniture"),
            new Product("P302", "Coffee Maker", "Home Appliances"),
            new Product("P150", "Headphones", "Electronics")
        };

        ECommerceSearch search = new ECommerceSearch(products);

        // Test searches
        System.out.println("=== Linear Search Tests ===");
        System.out.println("Search by ID P041: " + search.linearSearch("P041", "productId"));
        System.out.println("Search by Name 'Desk Chair': " + search.linearSearch("Desk Chair", "productName"));
        System.out.println("Search by Category 'Electronics': " + search.linearSearch("Electronics", "category"));

        System.out.println("\n=== Binary Search Tests ===");
        System.out.println("Search by ID P041: " + search.binarySearch("P041", "productId"));
        System.out.println("Search by Name 'Desk Chair': " + search.binarySearch("Desk Chair", "productName"));
        System.out.println("Search by Category 'Electronics': " + search.binarySearch("Electronics", "category"));

        // Performance comparison
        long startTime, endTime;
        String searchValue = "Desk Chair";
        String field = "productName";

        System.out.println("\n=== Performance Comparison ===");
        startTime = System.nanoTime();
        Product linearResult = search.linearSearch(searchValue, field);
        endTime = System.nanoTime();
        System.out.println("Linear Search Time: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        Product binaryResult = search.binarySearch(searchValue, field);
        endTime = System.nanoTime();
        System.out.println("Binary Search Time: " + (endTime - startTime) + " ns");
    }
}
