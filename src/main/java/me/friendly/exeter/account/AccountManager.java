package me.friendly.exeter.account;///*

import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import me.friendly.api.registry.ListRegistry;
import me.friendly.exeter.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public final class AccountManager extends ListRegistry<Account> {
    public AccountManager() {
        new Config("accounts.txt") {
            @Override
            public void load(Object... source) {
                try {
                    String readLine;
                    if (!this.getFile().exists()) {
                        this.getFile().createNewFile();
                    }
                    BufferedReader br = new BufferedReader(new FileReader(this.getFile()));
                    AccountManager.this.getRegistry().clear();
                    while ((readLine = br.readLine()) != null) {
                        try {
                            String[] split = readLine.split(":");
                            AccountManager.this.register(new Account(split[0], split[1]));
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    br.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void save(Object ... destination) {
                try {
                    if (!this.getFile().exists()) {
                        this.getFile().createNewFile();
                    }
                    BufferedWriter bw = new BufferedWriter(new FileWriter(this.getFile()));
                    for (Account account : AccountManager.this.getRegistry()) {
                        bw.write(account.getLabel() + ":" + account.getPassword());
                        bw.newLine();
                    }
                    bw.close();
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        };
    }

    public void login(String email, String password) throws AccountException {
        try {
            MicrosoftAuthenticator auth = new MicrosoftAuthenticator();
            MicrosoftAuthResult result = auth.loginWithCredentials(email, password);

            Minecraft.getMinecraft().session = new Session(result.getProfile().getName(), result.getProfile().getId(), result.getAccessToken(), "mojang");
        } catch (MicrosoftAuthenticationException e) {
            e.printStackTrace();
        }
    }
}

