import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Hoş geldiniz mesajı
        displayWelcomeMessage();

        while (true) {
            // Menü seçeneklerini görüntüle
            displayMenu();

            // Kullanıcı seçimi
            int choice = getUserChoice(scanner);

            // Çıkış kontrolü
            if (isExitChoice(choice)) {
                displayExitMessage();
                break;
            }

            // Geçersiz seçim kontrolü
            if (isInvalidChoice(choice)) {
                displayInvalidChoiceMessage();
                continue;
            }

            // Kullanıcıdan iki sayı al
            double num1 = getNumberFromUser(scanner, "Birinci sayıyı girin: ");
            double num2 = getNumberFromUser(scanner, "İkinci sayıyı girin: ");

            // İşlem sonucu
            double result = performCalculation(choice, num1, num2);

            // Hata durumunda devam et
            if (Double.isNaN(result)) {
                continue;
            }

            // Sonuç ekranı
            displayResult(choice, num1, num2, result);

            // Tekrar işlem yapmak isteyip istemediğini kontrol et
            if (!askToContinue(scanner)) {
                displayExitMessage();
                break;
            }
        }

        // Kaynakları kapat
        scanner.close();
    }

    // Hoş geldiniz mesajı
    public static void displayWelcomeMessage() {
        System.out.println("Hesap Makinesi Uygulamasına Hoş Geldiniz!");
        System.out.println("Bu uygulama ile dört işlem, mod alma ve üs alma işlemlerini gerçekleştirebilirsiniz.");
        System.out.println("Her adımda size rehberlik edilecektir.");
    }

    // Menü görüntüleme
    public static void displayMenu() {
        System.out.println("\nLütfen yapmak istediğiniz işlemi seçin:");
        System.out.println("1. Toplama (İki sayıyı toplar)");
        System.out.println("2. Çıkarma (İki sayıyı birbirinden çıkarır)");
        System.out.println("3. Çarpma (İki sayıyı çarpar)");
        System.out.println("4. Bölme (Birinci sayıyı ikinci sayıya böler)");
        System.out.println("5. Mod Alma (Birinci sayının ikinci sayıya göre modunu alır)");
        System.out.println("6. Üs Alma (Birinci sayının ikinci sayı kadar kuvvetini hesaplar)");
        System.out.println("7. Çıkış (Programı sonlandırır)");
    }

    // Kullanıcı seçimini al
    public static int getUserChoice(Scanner scanner) {
        System.out.print("Seçiminizi yapın (1-7): ");
        return scanner.nextInt();
    }

    // Çıkış seçimi kontrolü
    public static boolean isExitChoice(int choice) {
        return choice == 7;
    }

    // Geçersiz seçim kontrolü
    public static boolean isInvalidChoice(int choice) {
        return choice < 1 || choice > 7;
    }

    // Geçersiz seçim mesajı
    public static void displayInvalidChoiceMessage() {
        System.out.println("Geçersiz bir seçim yaptınız. Lütfen 1 ile 7 arasında bir değer giriniz.");
    }

    // Kullanıcıdan sayı alma
    public static double getNumberFromUser(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextDouble();
    }

    // Hesaplama işlemi
    public static double performCalculation(int choice, double num1, double num2) {
        switch (choice) {
            case 1:
                return add(num1, num2);
            case 2:
                return subtract(num1, num2);
            case 3:
                return multiply(num1, num2);
            case 4:
                return divide(num1, num2);
            case 5:
                return modulus(num1, num2);
            case 6:
                return power(num1, num2);
            default:
                System.out.println("Hata: Bilinmeyen işlem!");
                return Double.NaN;
        }
    }

    // Toplama işlemi
    public static double add(double num1, double num2) {
        System.out.println("Toplama işlemi seçildi.");
        return num1 + num2;
    }

    // Çıkarma işlemi
    public static double subtract(double num1, double num2) {
        System.out.println("Çıkarma işlemi seçildi.");
        return num1 - num2;
    }

    // Çarpma işlemi
    public static double multiply(double num1, double num2) {
        System.out.println("Çarpma işlemi seçildi.");
        return num1 * num2;
    }

    // Bölme işlemi
    public static double divide(double num1, double num2) {
        if (num2 == 0) {
            System.out.println("Hata: Bir sayı sıfıra bölünemez! İşlem iptal edildi.");
            return Double.NaN;
        }
        System.out.println("Bölme işlemi seçildi.");
        return num1 / num2;
    }

    // Mod alma işlemi
    public static double modulus(double num1, double num2) {
        System.out.println("Mod alma işlemi seçildi.");
        return num1 % num2;
    }

    // Üs alma işlemi
    public static double power(double num1, double num2) {
        System.out.println("Üs alma işlemi seçildi.");
        return Math.pow(num1, num2);
    }

    // Sonucu ekrana yazdır
    public static void displayResult(int choice, double num1, double num2, double result) {
        String operation = getOperationName(choice);
        System.out.printf("%s işleminin sonucu: %.2f\n", operation, result);
    }

    // İşlem adını al
    public static String getOperationName(int choice) {
        switch (choice) {
            case 1:
                return "Toplama";
            case 2:
                return "Çıkarma";
            case 3:
                return "Çarpma";
            case 4:
                return "Bölme";
            case 5:
                return "Mod Alma";
            case 6:
                return "Üs Alma";
            default:
                return "Bilinmeyen";
        }
    }

    // Kullanıcıdan devam etmek isteyip istemediğini sor
    public static boolean askToContinue(Scanner scanner) {
        System.out.print("\nBaşka bir işlem yapmak ister misiniz? (Evet/Hayır): ");
        scanner.nextLine(); // Satır sonunu temizle
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("evet")) {
            return true;
        } else if (response.equals("hayır")) {
            return false;
        } else {
            System.out.println("Geçersiz giriş! Program sonlandırılıyor.");
            return false;
        }
    }

    // Çıkış mesajı
    public static void displayExitMessage() {
        System.out.println("Hesap Makinesi Uygulamasından çıkış yapılıyor. Teşekkür ederiz!");
    }
}
