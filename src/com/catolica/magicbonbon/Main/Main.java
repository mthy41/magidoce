package com.catolica.magicbonbon.Main;
import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.text.rText;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;
import com.raylib.java.text.Font;
import java.util.ArrayList;
import java.util.Random;

import com.catolica.magicbonbon.Model.Gen;

import com.catolica.magicbonbon.Enums.Common;
import com.catolica.magicbonbon.Enums.LojaCommons;
import com.catolica.magicbonbon.Model.Generator;
import com.catolica.magicbonbon.Model.LojaCard;
import com.catolica.magicbonbon.Model.LojaDoce;
import com.catolica.magicbonbon.Model.Player;
import com.catolica.magicbonbon.Model.Tier4;
import com.catolica.magicbonbon.Model.Button;
import com.catolica.magicbonbon.Model.DoceStarter;
import com.catolica.magicbonbon.Model.FallingEntity;

// import com.catolica.magicbonbon.Model.Element;

public class Main {
    public static void main(String[] args){

        System.out.println(Common.SCREEN_HEIGHT.getValue()/2);
 
        Raylib r = new Raylib();    //Creating a core module instance.
        // SEMPRE LEMBRAR DE PASSAR ESSE
        // O MODULO PRO CONSTRUCTOR!!!!


        /* ---- Initializing required variables and data. ---- */                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
        final int SCREEN_WIDTH = Common.SCREEN_WIDTH.getValue();
        final int SCREEN_HEIGHT = Common.SCREEN_HEIGHT.getValue();
        final int FRAME_RATE = Common.FRAME_RATE.getValue();
        final int XMIN = 0;
        final int XMAX = Common.SCREEN_WIDTH.getValue();
        int lojaScrollControl = 0;
        int scrollSpeed = 30;
        String currentScreen = "GAMEPLAY";    //Starter screen variable

        int frameCounter = 0;    /*Sim... sim eu sei. A quantidade de frames pode
        exceder o limite de 32bits e crashar o jogo. Mas pra isso acontecer 
        vc teria que deixar o jogo aberto por 9.942 horas então dane-se.*/

        r.core.InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Magidoce");    //Window init
        r.core.SetTargetFPS(FRAME_RATE);    //Lock FPS
        /* --------------------------------------------------- */


        /* ------------- Initializing game data -------------- */                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
        Texture2D[] cardTextureDatapack = new Texture2D[3];
        cardTextureDatapack[0] = rTextures.LoadTexture("gen_locked.png");
        cardTextureDatapack[1] = rTextures.LoadTexture("gen_unknown.png");
        cardTextureDatapack[2] = rTextures.LoadTexture("gen_blank.png");

        Texture2D[] tierTextureDatapack = new Texture2D[5];
        tierTextureDatapack[0] = rTextures.LoadTexture("t0.png");
        tierTextureDatapack[1] = rTextures.LoadTexture("t1.png");
        tierTextureDatapack[2] = rTextures.LoadTexture("t2.png");
        tierTextureDatapack[3] = rTextures.LoadTexture("t3.png");
        tierTextureDatapack[4] = rTextures.LoadTexture("t4.png");

        Texture2D[] buttonTextureDatapack = new Texture2D[5];
        buttonTextureDatapack[0] = rTextures.LoadTexture("btn_1.png");
        buttonTextureDatapack[1] = rTextures.LoadTexture("btn_2.png");
        buttonTextureDatapack[2] = rTextures.LoadTexture("btn_3.png");
        buttonTextureDatapack[3] = rTextures.LoadTexture("btn_0.png"); //invisible button                
        buttonTextureDatapack[4] = rTextures.LoadTexture("btn_4.png"); //loja button

        Texture2D[] lojaCardTextureDatapack = new Texture2D[2];
        lojaCardTextureDatapack[0] = rTextures.LoadTexture("lojacard_blank.png");
        lojaCardTextureDatapack[1] = rTextures.LoadTexture("lojacard_locked.png");

        Font currencyFont = r.text.LoadFont("graph-35-pix.regular.ttf");

        /* --------------------------------------------------- */                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           



        /* ---------------Initializing gens------------------- */

        ArrayList<Gen> genBuffer = new ArrayList<Gen>();
        float cardGenYOffset = 16.0f;

        Gen generator = new Gen(
            r,
            new Texture2D(),
            new Vector2(0.0f, 0.0f + cardGenYOffset),
            Color.WHITE,
            0.0f,
            1.5f,
            100.0f,
            "Doceria",
            new Tier4(10.0f, 50.0f, 100.0f, 300.0f),
            new Button(r, new Texture2D("btn_1.png"), new Vector2(0.0f,0.0f), Color.WHITE, 0.0f, 1.5f, "", Color.BLACK, 20, new Vector2()),
            tierTextureDatapack,
            cardTextureDatapack,
            buttonTextureDatapack,
            0,
            new DoceStarter(new Texture2D("candy_1.png"), 2.0f)
        );

        Gen generator_1 = new Gen(
            r,
            new Texture2D(),
            new Vector2(0.0f, 105.0f + cardGenYOffset),
            Color.WHITE,
            0.0f,
            1.5f,
            500.0f,
            "Sorveteria",
            new Tier4(10.0f, 50.0f, 100.0f, 300.0f),
            new Button(r, new Texture2D("btn_1.png"), new Vector2(0.0f,0.0f), Color.WHITE, 0.0f, 1.5f, "", Color.BLACK, 20, new Vector2()),
            tierTextureDatapack,
            cardTextureDatapack,
            buttonTextureDatapack,
            0,
            new DoceStarter(new Texture2D("icecream_1.png"), 3.0f)
        );

        Gen generator_2 = new Gen(
            r,
            new Texture2D(),
            new Vector2(0.0f, 210.0f + cardGenYOffset),
            Color.WHITE,
            0.0f,
            1.5f,
            2600.0f,
            "Confeitaria",
            new Tier4(10.0f, 50.0f, 100.0f, 300.0f),
            new Button(r, new Texture2D("btn_1.png"), new Vector2(0.0f,0.0f), Color.WHITE, 0.0f, 1.5f, "", Color.BLACK, 20, new Vector2()),
            tierTextureDatapack,
            cardTextureDatapack,
            buttonTextureDatapack,
            0,
            new DoceStarter(new Texture2D("cake_1.png"), 0.0f)
        );

        Gen generator_3 = new Gen(
            r,
            new Texture2D(),
            new Vector2(0.0f, 315.0f + cardGenYOffset),
            Color.WHITE,
            0.0f,
            1.5f,
            8000.0f,
            "Chocolateria",
            new Tier4(10.0f, 50.0f, 100.0f, 300.0f),
            new Button(r, new Texture2D("btn_1.png"), new Vector2(0.0f,0.0f), Color.WHITE, 0.0f, 1.5f, "", Color.BLACK, 20, new Vector2()),
            tierTextureDatapack,
            cardTextureDatapack,
            buttonTextureDatapack,
            0,
            new DoceStarter(new Texture2D("chocolate_1.png"), 0.0f)
        );

        genBuffer.add(generator);
        genBuffer.add(generator_1);
        genBuffer.add(generator_2);
        genBuffer.add(generator_3);
        genBuffer.get(0).letVisible();

        ArrayList<Generator> genList = new ArrayList<Generator>();
        ArrayList<FallingEntity> doceBuffer = new ArrayList<>();


        /* ------------------Initializing doces--------------------- */

        LojaDoce doce_1 = new LojaDoce(
            new Texture2D("candy_2.png"), 
            3.0f, 
            "Maracudoce", 
            "Doce de maracujá\npara sonos doces!", 
            30.0f
            );

        LojaDoce doce_2 = new LojaDoce(
            new Texture2D("candy_3.png"), 
            5.0f, 
            "Pirulito", 
            "Um clássico que\nnão pode faltar!", 
            50.0f
            );

        LojaDoce doce_3 = new LojaDoce(
            new Texture2D("candy_4.png"), 
            8.0f, 
            "Bengala", 
            "Nunca é cedo para\ndoces natalinos!", 
            80.0f
            );

        LojaDoce doce_4 = new LojaDoce(
            new Texture2D("popsicle_1.png"), 
            15.0f, 
            "Picolé oriental", 
            "Um gelado de cores\ndiversas.", 
            120.0f
            );

        LojaDoce doce_5 = new LojaDoce(
            new Texture2D("popsicle_2.png"), 
            19.0f, 
            "Picolé azedo", 
            "Para equilibrar\na doçura do coração!", 
            180.0f
            );

        LojaDoce doce_6 = new LojaDoce(
            new Texture2D("popsicle_3.png"), 
            26.0f, 
            "Picolancia", 
            "Um picolé com\nformato de melancia.", 
            310.0f
            );

        LojaDoce doce_7 = new LojaDoce(
            new Texture2D("cake_2.png"), 
            34.0f, 
            "Bolo de mulango", 
            "Um bolo nostálgico\ne cartunesco.", 
            400.0f
            );

        LojaDoce doce_8 = new LojaDoce(
            new Texture2D("cake_3.png"), 
            40.0f, 
            "Redvelvet", 
            "Tem gosto de...\nvermelho!", 
            550.0f
            );
    
        LojaDoce doce_9 = new LojaDoce(
            new Texture2D("brizadeiro.png"), 
            100.0f, 
            "Brizadeiro", 
            "Doce proibido! Pode\ncausar efeitos adversos!", 
            1000.0f
            );

        LojaDoce doce_10 = new LojaDoce(
            new Texture2D("chocolate_2.png"), 
            60.0f, 
            "Chocolate ao leite", 
            "Um doce cremoso e\nelegante.", 
            1500.0f
            );

        LojaDoce doce_11 = new LojaDoce(
            new Texture2D("trufas.png"), 
            3.0f, 
            "Trufas", 
            "Recheados de chocolate\ne romance.", 
            2000.0f
            );

        LojaDoce doce_12 = new LojaDoce(
            new Texture2D("chocolate_3.png"), 
            3.0f, 
            "Chocolate com pimenta", 
            "Para paladares\nresistentes e ousados!", 
            3000.0f
            );



        /* ------------------Initializing loja cards--------------------- */

        ArrayList<LojaCard> lojaCardList = new ArrayList<>();

        lojaCardList.add(new LojaCard(
            lojaCardTextureDatapack,
            doce_1,
            0,
            r,
            lojaCardTextureDatapack[1],
            new Vector2(0.0f, 0.0f),
            Color.WHITE,
            0.0f,
            6,
            new Button(r, new Texture2D("btn_1.png"), new Vector2(0.0f,0.0f), Color.WHITE, 0.0f, 2.0f, "", Color.WHITE, 20, new Vector2())
        ));
        lojaCardList.add(new LojaCard(
            lojaCardTextureDatapack,
            doce_2,
            0,
            r,
            lojaCardTextureDatapack[1],
            new Vector2(0.0f, 0.0f),
            Color.WHITE,
            0.0f,
            6,
            new Button(r, new Texture2D("btn_1.png"), new Vector2(0.0f,0.0f), Color.WHITE, 0.0f, 2.0f, "", Color.WHITE, 20, new Vector2())
        ));
        lojaCardList.add(new LojaCard(
            lojaCardTextureDatapack,
            doce_3,
            0,
            r,
            lojaCardTextureDatapack[1],
            new Vector2(0.0f, 0.0f),
            Color.WHITE,
            0.0f,
            6,
            new Button(r, new Texture2D("btn_1.png"), new Vector2(0.0f,0.0f), Color.WHITE, 0.0f, 2.0f, "", Color.WHITE, 20, new Vector2())
        ));
        lojaCardList.add(new LojaCard(
            lojaCardTextureDatapack,
            doce_4,
            1,
            r,
            lojaCardTextureDatapack[1],
            new Vector2(0.0f, 0.0f),
            Color.WHITE,
            0.0f,
            6,
            new Button(r, new Texture2D("btn_1.png"), new Vector2(0.0f,0.0f), Color.WHITE, 0.0f, 2.0f, "", Color.WHITE, 20, new Vector2())
        ));
        lojaCardList.add(new LojaCard(
            lojaCardTextureDatapack,
            doce_5,
            1,
            r,
            lojaCardTextureDatapack[1],
            new Vector2(0.0f, 0.0f),
            Color.WHITE,
            0.0f,
            6,
            new Button(r, new Texture2D("btn_1.png"), new Vector2(0.0f,0.0f), Color.WHITE, 0.0f, 2.0f, "", Color.WHITE, 20, new Vector2())
        ));
        lojaCardList.add(new LojaCard(
            lojaCardTextureDatapack,
            doce_6,
            1,
            r,
            lojaCardTextureDatapack[1],
            new Vector2(0.0f, 0.0f),
            Color.WHITE,
            0.0f,
            6,
            new Button(r, new Texture2D("btn_1.png"), new Vector2(0.0f,0.0f), Color.WHITE, 0.0f, 2.0f, "", Color.WHITE, 20, new Vector2())
        ));
        lojaCardList.add(new LojaCard(
            lojaCardTextureDatapack,
            doce_7,
            2,
            r,
            lojaCardTextureDatapack[1],
            new Vector2(0.0f, 0.0f),
            Color.WHITE,
            0.0f,
            6,
            new Button(r, new Texture2D("btn_1.png"), new Vector2(0.0f,0.0f), Color.WHITE, 0.0f, 2.0f, "", Color.WHITE, 20, new Vector2())
        ));
        lojaCardList.add(new LojaCard(
            lojaCardTextureDatapack,
            doce_8,
            2,
            r,
            lojaCardTextureDatapack[1],
            new Vector2(0.0f, 0.0f),
            Color.WHITE,
            0.0f,
            6,
            new Button(r, new Texture2D("btn_1.png"), new Vector2(0.0f,0.0f), Color.WHITE, 0.0f, 2.0f, "", Color.WHITE, 20, new Vector2())
        ));
        lojaCardList.add(new LojaCard(
            lojaCardTextureDatapack,
            doce_9,
            2,
            r,
            lojaCardTextureDatapack[1],
            new Vector2(0.0f, 0.0f),
            Color.WHITE,
            0.0f,
            6,
            new Button(r, new Texture2D("btn_1.png"), new Vector2(0.0f,0.0f), Color.WHITE, 0.0f, 2.0f, "", Color.WHITE, 20, new Vector2())
        ));
        lojaCardList.add(new LojaCard(
            lojaCardTextureDatapack,
            doce_10,
            3,
            r,
            lojaCardTextureDatapack[1],
            new Vector2(0.0f, 0.0f),
            Color.WHITE,
            0.0f,
            6,
            new Button(r, new Texture2D("btn_1.png"), new Vector2(0.0f,0.0f), Color.WHITE, 0.0f, 2.0f, "", Color.WHITE, 20, new Vector2())
        ));
        lojaCardList.add(new LojaCard(
            lojaCardTextureDatapack,
            doce_11,
            3,
            r,
            lojaCardTextureDatapack[1],
            new Vector2(0.0f, 0.0f),
            Color.WHITE,
            0.0f,
            6,
            new Button(r, new Texture2D("btn_1.png"), new Vector2(0.0f,0.0f), Color.WHITE, 0.0f, 2.0f, "", Color.WHITE, 20, new Vector2())
        ));
        lojaCardList.add(new LojaCard(
            lojaCardTextureDatapack,
            doce_12,
            3,
            r,
            lojaCardTextureDatapack[1],
            new Vector2(0.0f, 0.0f),
            Color.WHITE,
            0.0f,
            6,
            new Button(r, new Texture2D("btn_1.png"), new Vector2(0.0f,0.0f), Color.WHITE, 0.0f, 2.0f, "", Color.WHITE, 20, new Vector2())
        ));

        //Align and stack the cards
        for (int i = 0; i < lojaCardList.size(); i++){
            lojaCardList.get(i).setNewPosition(240.0f * i + LojaCommons.LOJACARDGAPS.getValue(), 80.0f);
        }


        Player player = new Player();


        Button lojaButton = new Button(
            r, 
            buttonTextureDatapack[4], 
            new Vector2(SCREEN_WIDTH - 90, 5), 
            Color.WHITE, 
            0.0f, 
            2.0f, 
            "LOJA", 
            Color.WHITE, 
            20, 
            new Vector2(18.0f,15.0f)
            );

        Button backButton = new Button(
            r, 
            buttonTextureDatapack[2], 
            new Vector2(SCREEN_WIDTH - 90, 5), 
            Color.WHITE, 
            0.0f, 
            2.0f, 
            "voltar", 
            Color.WHITE, 
            20, 
            new Vector2(14.0f,15.0f)
            );


        while(!r.core.WindowShouldClose()){    //Main loop event

        /* ------------------SCENE UPDATE--------------------- */
            switch (currentScreen) {
                case "LOGO":
                    //TODO: Make the logo screen and update variables.
                    frameCounter++;
                    if (frameCounter > 180){   //3 seconds delay
                        currentScreen = "MENU";
                    }
                    break;
                default:
                    break;
            }
        /* --------------------------------------------------- */

        /* ---------------OPENGL CONTEXT UPDATE--------------- */
            r.core.BeginDrawing();    //Start OPENGL context
            frameCounter++;
                switch (currentScreen) {
                    case "LOGO":
                        //TODO: Draw the logo stuff
                        r.text.DrawText("loading test...", 0, SCREEN_HEIGHT - 20, 20, Color.BLACK);
                        break;

                    case "MENU":
                        //TODO: Draw the menu stuff
                        r.text.DrawTextPro(rText.GetFontDefault(), "MAGIDOCE!", new Vector2(SCREEN_WIDTH/2, SCREEN_WIDTH/2), new Vector2(0, 0), 0.0f, 50, 1, Color.BLACK);
                        if (r.core.IsKeyPressed(257)){ //257 is the code for the ENTER key
                            currentScreen = "GAMEPLAY";
                        }
                        break;

                    case "GAMEPLAY":
                        for (int i = 0; i < doceBuffer.size(); i++){
                            doceBuffer.get(i).draw();
                            doceBuffer.get(i).fall();
                            doceBuffer.get(i).spin();
                        } 
                        r.core.ClearBackground(Color.LIGHTGRAY);
                        lojaButton.draw();
                        if (lojaButton.checkClick()){
                            currentScreen = "LOJA";
                        }

                        r.text.DrawTextPro(
                            currencyFont, //FONT
                            player.getCurrencyString(), //STRING
                            new Vector2(SCREEN_WIDTH - 80 - (player.getCurrencyString().length() * 35), 15.0f), //POSITION
                            new Vector2(0.0f, 0.0f), //ORIGIN
                            0.0f, //ROTATION
                            35.0f, //FONT SIZE
                            0.0f, //SPACING
                            Color.DARKGRAY); //COLOR

                        for (int i = 0; i < genBuffer.size(); i++){
                            genBuffer.get(i).draw();
                            if (genBuffer.get(i).checkState().equals("AVAILABLE") && genBuffer.get(i).button.checkClick()){
                                if (player.buyGenerator(genBuffer.get(i))){
                                    genBuffer.get(i).own();
                                    try {
                                        genBuffer.get(i+1);
                                    } catch (IndexOutOfBoundsException f){
                                        continue;
                                    }
                                    genBuffer.get(i+1).letVisible();
                                    continue;
                                }
                            }

                            if (genBuffer.get(i).checkState().equals("OWNING") && genBuffer.get(i).button.checkClick()){
                                if (player.buyGenUpgrade(genBuffer.get(i).tier)){
                                    genBuffer.get(i).upgradeTier();
                                    continue;
                                }
                            }

                            if (genBuffer.get(i).checkState().equals("OWNING")){
                                if (genBuffer.get(i).run()){
                                    float fullDrop = genBuffer.get(i).doceStarter.getDropValue();
                                    for (int j = 0; j < genBuffer.get(i).boughtDoces.size(); j++){
                                        fullDrop += genBuffer.get(i).boughtDoces.get(j).dropValue;
                                    }
                                    player.addCurrency(fullDrop);
                                    doceBuffer.add(new FallingEntity(
                                        r, 
                                        genBuffer.get(i).getRandDoceTexture(), 
                                        new Vector2((float)(Math.random() * (200 - SCREEN_WIDTH) + SCREEN_WIDTH), -20.0f), 
                                        Color.WHITE, 
                                        0, 
                                        2.0f, 
                                        2.5f
                                        ));
                                }
                            }
                        }
                           

                        break;

                    case "LOJA":
                    r.core.ClearBackground(Color.LIME);
                    r.text.DrawTextPro(
                        currencyFont, //FONT
                        player.getCurrencyString(), //STRING
                        new Vector2(SCREEN_WIDTH - 80 - (player.getCurrencyString().length() * 35), 15.0f), //POSITION
                        new Vector2(0.0f, 0.0f), //ORIGIN
                        0.0f, //ROTATION
                        35.0f, //FONT SIZE
                        0.0f, //SPACING
                        Color.RAYWHITE); //COLOR

                        for (int i = 0; i < genBuffer.size(); i++){
                            Gen currentGen = genBuffer.get(i);
                            if (currentGen.checkIsOwning()){
                                for (int j = 0; j < lojaCardList.size(); j++){
                                    LojaCard currentCard = lojaCardList.get(j);
                                    if (currentCard.genOwnerIndex == i && !currentCard.checkCardState().equals("OWNING")){
                                        currentCard.letVisible();
                                    }
                                }
                            }
                        }

                        int scroll = (int) rCore.GetMouseWheelMove();
                        if (scroll < 0 && lojaScrollControl >= LojaCommons.LOJASCROLLMAX.getValue()){
                            for (int i = 0; i < lojaCardList.size(); i++){
                                LojaCard currentLojaCard = lojaCardList.get(i);
                                currentLojaCard.position.x += scroll * scrollSpeed;
                            }
                            lojaScrollControl += scroll * scrollSpeed;
                        }

                        if (scroll > 0 && lojaScrollControl <= 0){
                            for (int i = 0; i < lojaCardList.size(); i++){
                                LojaCard currentLojaCard = lojaCardList.get(i);
                                currentLojaCard.position.x += scroll * scrollSpeed;
                            }
                            lojaScrollControl += scroll * scrollSpeed;                            
                        }

                        for (int i = 0; i < lojaCardList.size(); i++){
                            LojaCard currentLojaCard = lojaCardList.get(i);                            
                            currentLojaCard.draw();
                            if (currentLojaCard.button.checkClick()){
                                if (player.buyDoce(currentLojaCard.doce)){
                                    genBuffer.get(currentLojaCard.genOwnerIndex).addDoce(currentLojaCard.doce);
                                    currentLojaCard.own();
                                }
                            }
                        }
                        backButton.draw();
                        if (backButton.checkClick()){
                            currentScreen = "GAMEPLAY";
                        }
                        //TODO: Draw the loja stuff
                        break;

                    default:
                    r.core.ClearBackground(Color.LIGHTGRAY);

                        break;
                }

            r.text.DrawFPS(0, 0);

            r.core.EndDrawing();    //End OPENGL context
        }
        /* --------------------------------------------------- */


    }
}
