package com.example.oneday.Models;

import java.util.ArrayList;

public class WalletProfile {
    String id;
    int coins;
    ArrayList<PurchaseModel> purchaseHistory;
    ArrayList<SpentModel> spentHistory;

    public WalletProfile() {
    }
}
