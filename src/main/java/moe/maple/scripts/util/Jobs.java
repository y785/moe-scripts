package moe.maple.scripts.util;

import java.util.Map;

/**
 * @author umbreon22
 * Created on 9/16/2019.
 */
public class Jobs {

    public static final int
        Beginner = 0,

        //many men and women
        Swordman = 100,
        Fighter = 110,
        Crusader = 111,
        Hero = 112,
        Page = 120,
        WhiteKnight = 121,
        Paladin = 122,
        Spearman = 130,
        DragonKnight = 131,
        DarkKnight = 132,

        //wizards
        Magician = 200,
        FirePoisonWizard = 210,
        FirePoisonMage = 211,
        FirePoisonArchmage = 212,
        IceLightningWizard = 220,
        IceLightningMage = 221,
        IceLightningArchmage = 222,
        Cleric = 230,
        Priest = 231,
        Bishop = 232,

        //bowguys
        Archer = 300,
        Hunter = 310,
        Ranger = 311,
        Bowmaster = 312,
        Crossbowman = 320,
        Sniper = 321,
        Marksman = 322,

        //stole my heart
        Rogue = 400,
        Assassin = 410,
        Hermit = 411,
        NightLord = 412,
        Bandit = 420,
        ChiefBandit = 421,
        Shadower = 422,

        //dual bladers TODO
        BladeLord = 433,

        //booty chasers
        Pirate = 500,
        Brawler = 510,
        Marauder = 511,
        Buccaneer = 512,
        Gunslinger = 520,
        Outlaw = 521,
        Corsair = 522
                ;

    public static final int[][] JOB_ADVANCEMENTS = {
            { Jobs.Beginner, Jobs.Swordman, Jobs.Magician, Jobs.Archer, Jobs.Rogue, Jobs.Pirate },
            { Jobs.Fighter, Jobs.Page, Jobs.Spearman, Jobs.FirePoisonWizard, Jobs.IceLightningWizard, Jobs.Cleric, Jobs.Hunter, Jobs.Crossbowman, Jobs.Assassin, Jobs.Bandit, Jobs.Brawler, Jobs.Gunslinger},
            { Jobs.Crusader, Jobs.WhiteKnight, Jobs.DragonKnight, Jobs.FirePoisonMage, Jobs.IceLightningMage, Jobs.Priest, Jobs.Ranger, Jobs.Sniper, Jobs.Hermit, Jobs.ChiefBandit, Jobs.Marauder, Jobs.Outlaw},
            { Jobs.Hero, Jobs.Paladin, Jobs.DarkKnight, Jobs.FirePoisonArchmage, Jobs.IceLightningArchmage, Jobs.Bishop, Jobs.Bowmaster, Jobs.Marksman, Jobs.NightLord, Jobs.Shadower, Jobs.Buccaneer, Jobs.Corsair}
    };


    private static final Map<Integer, String> jobNames = Map.ofEntries(
            Map.entry(Beginner, "Beginner"),

            //warriors
            Map.entry(Swordman, "Swordman"),
            Map.entry(Fighter, "Fighter"),
            Map.entry(Crusader, "Crusader"),
            Map.entry(Hero, "Hero"),
            Map.entry(WhiteKnight, "White Knight"),
            Map.entry(Paladin, "Paladin"),
            Map.entry(DragonKnight, "Dragon Knight"),
            Map.entry(DarkKnight, "Dark Knight"),

            //magic babes
            Map.entry(Magician, "Magician"),
            Map.entry(FirePoisonWizard, "Fire/Poison Wizard"),
            Map.entry(FirePoisonMage, "Fire/Poison Mage"),
            Map.entry(FirePoisonArchmage, "Fire/Poison Arch Mage"),
            Map.entry(IceLightningWizard, "Ice/Lightning Wizard"),
            Map.entry(IceLightningMage, "Ice/Lightning Mage"),
            Map.entry(IceLightningArchmage, "Ice/Lightning Arch Mage"),
            Map.entry(Cleric, "Cleric"),
            Map.entry(Priest, "Priest"),
            Map.entry(Bishop, "Bishop"),

            //bowguys
            Map.entry(Archer, "Archer"),
            Map.entry(Hunter, "Hunter"),
            Map.entry(Ranger, "Ranger"),
            Map.entry(Bowmaster, "Bowmaster"),
            Map.entry(Crossbowman, "Crossbowman"),
            Map.entry(Sniper, "Sniper"),
            Map.entry(Marksman, "Marksman"),

            //thieves
            Map.entry(Rogue, "Rogue"),
            Map.entry(Assassin, "Assassin"),
            Map.entry(Hermit, "Hermit"),
            Map.entry(NightLord, "Night Lord"),
            Map.entry(Bandit, "Bandit"),
            Map.entry(ChiefBandit, "Chief Bandit"),
            Map.entry(Shadower, "Shadower"),

            //pirates
            Map.entry(Pirate, "Pirate"),
            Map.entry(Brawler, "Brawler"),
            Map.entry(Marauder, "Marauder"),
            Map.entry(Buccaneer, "Buccaneer"),
            Map.entry(Gunslinger, "Gunslinger"),
            Map.entry(Outlaw, "Outlaw"),
            Map.entry(Corsair, "Corsair")

    );

    public static String getName(int jobId) {
        return jobNames.getOrDefault(jobId, "INVALID_JOB");
    }
}
