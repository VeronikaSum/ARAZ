package controller;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class ProneNumberGenerator implements Serializable {
    private static final long serialVersionUID = 5362490420859689032L;

    private transient CompletableFuture<String> randomNumberGenerationTask = null;
    private final Random random = new Random();

    public String generatePhoneNumber() {
        randomNumberGenerationTask = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }

            int targetNumberLength = 7;
            StringBuilder number = new StringBuilder("");

            for (int i = 0; i < targetNumberLength; i++) {
                int randomLimitedInt = random.nextInt(10);
                number.append(randomLimitedInt);
            }

            return number.toString();
        });

        return "index?faces-redirect=true";
    }

    public boolean isPhoneNumberGeneratorRunning() {
        return randomNumberGenerationTask != null && !randomNumberGenerationTask.isDone();
    }

    public String getPhoneNumberGeneratorStatus() throws ExecutionException, InterruptedException {
        if (randomNumberGenerationTask == null) {
            return null;
        } else if (isPhoneNumberGeneratorRunning()) {
            return "Phone number generation in progress";
        }
        return "Phone number generated 86" + randomNumberGenerationTask.get();
    }
}