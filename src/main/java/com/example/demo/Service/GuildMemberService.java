package com.example.demo.Service;

import com.example.demo.Model.GuildMember;
import com.example.demo.Model.Payments;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GuildMemberService {

    public List<GuildMember> processLuaFile() {
        String filePath = "C:\\Users\\cuchi\\Documents\\Elder Scrolls Online\\live\\SavedVariables\\1.lua";
        return readLuaFile(filePath);
    }

    private List<GuildMember> readLuaFile(String filePath) {
        List<GuildMember> members = new ArrayList<>();
        Map<String, Payments> paymentsMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("Members")) {
                    break;
                }
            }

            while ((line = br.readLine()) != null) {
                if (isMemberStep(line)) {
                    members.add(memberParser(br, line));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return members;
    }

    private static boolean isMemberStep(String line) {
        Pattern pattern = Pattern.compile("\\[\\d+\\]");
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }


    private static GuildMember memberParser(BufferedReader br, String line) throws IOException{
        GuildMember member = new GuildMember();

        br.readLine();

        for (int i = 0; i < 5; i++) {
            line = br.readLine();
            setParameter(parseParamName(line), getParameter(line), member); //set other parameters
        }

        return member;

    }

    private static String getParameter(String line) {
        return line.substring(getIndex(line, "=") + 2, getIndex(line, ",")).replaceAll("\"", "");
    }

    private static void setParameter(String paramName, String value, GuildMember member) {
        switch (paramName) {
            case "accName" -> member.setUsername(value);
            case "note" -> member.setNotes(value);
            case "lastOn" -> member.setTime(value);
            case "rankId" -> addRank(Integer.parseInt(value), member);
        }
    }

    private static String parseParamName(String line) {
        return line.substring(getIndex(line, "\\[") + 2, getIndex(line, "\\]") - 1);

    }

    private static String addRank(int value, GuildMember member) {
        switch (value) {
            case 1 -> member.setRank("Глава");
            case 2 -> member.setRank("Магистр");
            case 3 -> member.setRank("Герой");
            case 4 -> member.setRank("Рыцарь");
            case 5 -> member.setRank("Элитный воин");
            case 6 -> member.setRank("Опытный воин");
            case 7 -> member.setRank("Мастер Крафтер");
            case 8 -> member.setRank("Воин");
            case 9 -> member.setRank("Молчун");
            case 10 -> member.setRank("Странник");
        }
        return null;
    }

    private static int getIndex (String line, String reg) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            return matcher.start();
        }
        return 0;
    }


}
