package it.paleocapa.mastroiannim;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.*;


@Service
public class JavaBossBot extends TelegramLongPollingBot {
	private Bar b=new Bar();
	private static final Logger LOG = LoggerFactory.getLogger(JavaBossBot.class);

	private String botUsername;
	private static String botToken;
	private static JavaBossBot instance;

	public static JavaBossBot getJavaBossBotInstance(String botUsername, String botToken){
		if(instance == null) {
			instance = new JavaBossBot();
			instance.botUsername = botUsername;
			JavaBossBot.botToken = botToken;
		}
		return instance;
	}

	private JavaBossBot(){
		super(botToken);
	}

	@Override
	public String getBotToken() {
		return botToken;
	}
	
	@Override
	public String getBotUsername() {
		return botUsername;
	}

		@Override
		public void onUpdateReceived(Update update) {
			//scrivi un programma che implmenta un bot che segua i metodi della classe Bar
			//e che risponda alle seguenti richieste:
			// /start: inizia la conversazione
			// /menu: stampa la lista cib
			// /ordina: ordina un cibo con nomeCognome, nomeCibo e soldi che vengono inseriti
			// /paga: paga il conto con nomeCognome
			// /controlla: controlla il conto con nomeCognome
			// /fine: termina la conversazione
			//se il messaggio è /start
			//invia un messaggio di benvenuto
			//se il messaggio è /menu
			//invia un messaggio con il menu
			//se il messaggio è /ordina
			//invia un messaggio che dice di inserire nomeCognome, nomeCibo e soldi
			//se il messaggio è /paga
			//invia un messaggio che dice quanto si deve pagare e cosa ti rimane
			// inizia a programmare da qui

			if(update.hasMessage() && update.getMessage().hasText()) {
				String message_text = update.getMessage().getText();
				long chat_id = update.getMessage().getChatId();
				SendMessage message = new SendMessage();
				message.setChatId(String.valueOf(chat_id));
				message.setText(message_text);
				try {
					execute(message);
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}
			//inizia con il messaggio di benvenuto
			if(update.getMessage().getText().equals("/start")){
				SendMessage message = new SendMessage();
				message.setChatId(String.valueOf(update.getMessage().getChatId()));
				message.setText("Benvenuto nel bar di JavaBossBot");
				try {
					execute(message);
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}
			//se il messaggio è /menu
			if(update.getMessage().getText().equals("/menu")){
				SendMessage message = new SendMessage();
				message.setChatId(String.valueOf(update.getMessage().getChatId()));
				message.setText("Menu: " + b.getMenu());
				try {
					execute(message);
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}
			//se il messaggio è /ordina
			if(update.getMessage().getText().equals("/ordina")){
				SendMessage message = new SendMessage();
				message.setChatId(String.valueOf(update.getMessage().getChatId()));
				message.setText("Inserisci nomeCognome, nomeCibo e soldi");
				String[] s = update.getMessage().getText().split(" ");
				b.aggiungiOrdine(s[1], s[2], Integer.parseInt(s[3]));
				try {
					execute(message);
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}
			

			

		}
}

