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
/*String nomeCognome = "";
		String nomePanino = "";
		if (update.hasMessage() && update.getMessage().hasText()) {
			long chatId = update.getMessage().getChatId();
			String userText = update.getMessage().getText();
			//String userText2 = update.getMessage().getText();
	
	
			SendMessage message = new SendMessage();
			message.setChatId(chatId);
	
			if (userText.equalsIgnoreCase("O")) {
				message.setText("Hai scelto di ordinare. Ecco la lista dei panini disponibili:\n"
					+ "Panino Hamburger, 2$\n"
					+ "Panino CordonBlue,3$\n"
					+ "Panino Prosciutto, 1$\n"
					+ "Panino Wurstel, 2$\n"
					+ "Panino Cotoletta, 2$\n"
					+ "Panino Arrosto, 2$\n"
					+ "Quale panino desideri?");
			} else if (userText.equalsIgnoreCase("Panino Hamburger") || userText.equalsIgnoreCase("Panino CordonBlue")
			|| userText.equalsIgnoreCase("Panino Prosciutto") || userText.equalsIgnoreCase("Panino Wurstel")
			|| userText.equalsIgnoreCase("Panino Cotoletta") || userText.equalsIgnoreCase("Panino Arrosto")) {
				nomePanino = userText;
				message.setText("Inserisci il tuo nome e cognome");  // Modifica il messaggio per richiedere il nome e cognome
			} else if (!nomePanino.isEmpty()) {
				nomeCognome = userText;
				b.aggiungiOrdine(nomeCognome, nomePanino, 2);
				Ordine o = b.listaCibo.get(nomePanino).getLast();
				System.out.println("ordine: " + o.getCibo() + ", " + o.getCliente());
			
				// Resetta le variabili per consentire nuovi ordini
				//nomePanino = "";
				//nomeCognome = "";
			} else {
				message.setText("Benvenuto nel Barbot di Silvia, inserisci 'O' per ordinare.");
			}
	
			try {
				execute(message);
			} catch (TelegramApiException e) {
				LOG.error(e.getMessage());
			}
		}*/
		@Override
		public void onUpdateReceived(Update update) {
			if (update.hasMessage() && update.getMessage().hasText()) {
				long chatId = update.getMessage().getChatId();
				String userText = update.getMessage().getText();
			
				SendMessage message = new SendMessage();
				message.setChatId(String.valueOf(chatId));
				
				if (userText.equals("/start")) {
					message.setText("Benvenuto nel BarBot di Silvia, per iniziare e vedere il menu scrivi /menu");
				} else if (userText.equals("/menu")) {
					StringBuilder menuText = new StringBuilder("Ecco il menu:\n");
					for (Cibo cibo : Bar.cib) {
						menuText.append("- ").append(cibo.getNome()).append(": €").append(cibo.getPrezzo()).append("\n");
					}
					message.setText(menuText.toString());
					try {
						execute(message);
					} catch (TelegramApiException e) {
						e.printStackTrace();
					}
					SendMessage orderMessage = new SendMessage();
					orderMessage.setChatId(String.valueOf(chatId));
					orderMessage.setText("Per ordinare scrivi /ordina");
					try {
						execute(orderMessage);
					} catch (TelegramApiException e) {
						e.printStackTrace();
					}
				} else if (userText.equals("/ordina")) {
					// Richiesta al cliente di inserire NomeCognome, NomePanino
					SendMessage orderRequestMessage = new SendMessage();
					orderRequestMessage.setChatId(String.valueOf(chatId));
					orderRequestMessage.setText("Inserisci NomeCognome, NomePanino, soldi (es. Mario Rossi, Panino Prosciutto, 5)");
					try {
						execute(orderRequestMessage);
					} catch (TelegramApiException e) {
						e.printStackTrace();
					}
				} else {
					// Gestione dell'input dell'utente per l'ordinazione
					String username = update.getMessage().getFrom().getUserName();
					String[] orderData = userText.split(", ");
					if (orderData.length == 2) {
						String nomeCognome = orderData[0];
						String nomePanino = orderData[1];
						int soldi=Integer.parseInt(orderData[2]);
						b.aggiungiOrdine(nomeCognome, nomePanino, soldi);
						SendMessage orderConfirmationMessage = new SendMessage();
						orderConfirmationMessage.setChatId(String.valueOf(chatId));
						orderConfirmationMessage.setText("Hai ordinato: " + nomePanino);
						try {
							execute(orderConfirmationMessage);
						} catch (TelegramApiException e) {
							e.printStackTrace();
						}
					} else {
						SendMessage errorMessage = new SendMessage();
						errorMessage.setChatId(String.valueOf(chatId));
						errorMessage.setText("Input non valido. Inserisci NomeCognome, NomePanino (es. Mario Rossi, Panino Prosciutto)");
						try {
							execute(errorMessage);
						} catch (TelegramApiException e) {
							e.printStackTrace();
						}
			
					}
				}
			}
		}
}

