package net.bynaryscode.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Log {
	
	public static final String INFORMATION_OBJET = "INFORMATION";
	public static final String ERREUR_OBJET = "ERREUR";
	public static final String WARNING_OBJET = "ATTENTION";
	
	/**
	 * Permet de charger un fichier {@code .log} dans l'
	 * application, sous la forme d'un objet {@link Log}.
	 * <p>Est charg� dans l'application tout le contenu du
	 * fichier {@code .log} sous forme de texte. 
	 * @param pathname - le fichier source, celui qui contient
	 * les informations sur le dique dur.
	 * @param log - un objet {@link Log} pour accueillir les
	 * donn�es. Toutes les donn�es de cet objet seront
	 * r�initialis�es avant de copier les nouvelles donn�es
	 * dedans. Si ce param�tre vaut {@code null} un nouvel
	 * objet sera cr��.
	 * @return L'objet {@link Log} qui contient les donn�es
	 * charg�es : soit celui pass� en param�tre, soit, si ce
	 * dernier valait {@code null}, le nouvel objet Log cr��.
	 */
	public static Log loadLog(File pathname, Log log) throws IOException {
		
		if (log == null) {
			log = new Log();
		}
		
		log.file = pathname;
		log.contents.clear();
		
		BufferedReader str = null;
		
		try {
			str = new BufferedReader(new FileReader(pathname));
			String line = "";
			while ((line = str.readLine()) != null) {
				log.contents.add(line);
			}
		} 
		finally {
			if (str != null) {
				try {
					str.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		log.updated = true;
		
		return log;
	}
	
	public static void writeLog(Log log) throws IOException {
		
		if (log == null) throw new NullPointerException("log == null !");
		if (log.file == null) throw new NullPointerException("pas de fichier rattach� au log.");
		
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(log.file));
			for (String line : log.contents) {
				writer.write(line + "\r\n");
			}
		}
		finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		log.updated = true;
	}
	
	private static LogListener SYSOUT_LISTENER = new LogListener() {
		
		@Override
		public void logEntry(String entry, Log log) {
			System.out.println(entry);
		}
	};
	
	
	
	private File file;
	/** Cette liste contient les lignes du log. */
	private ArrayList<String> contents = new ArrayList<String>();
	private ExceptionParser parser;
	
	/** Indique si le contenu du fichier {@link #file} est identique au contenu
	 * de cet objet {@link Log}
	 * @see #contents  */
	private boolean updated = false;
	
	//Marqueurs
	private boolean hasError = false;
	private boolean hasWarning = false;
	
	private ArrayList<LogListener> listeners = new ArrayList<LogListener>();
	
	public Log() {
		this(new DefaultExceptionParser());
	}
	
	public Log(ExceptionParser parser) {
		this.parser = parser == null ? new DefaultExceptionParser() : parser;
	}
	
	public void setExceptionParser(ExceptionParser parser) {
		if (parser == null) throw new IllegalArgumentException("parser == null");
		this.parser = parser;
	}
	
	public ExceptionParser getExceptionParser() {
		return this.parser;
	}
	
	public void setFile(File f) {
		if (f == null) throw new NullPointerException("f ne peut �tre null.");
		
		this.file = f;
	}
	
	public File getFile() {
		return this.file;
	}
	
	/**
	 * Ajoute un titre de session au log. S'utilise pour s�parer
	 * les traitements ind�pendant.
	 * @param sessionName - Le nom � donner � la session.
	 */
	public void createNewSession(String sessionName) {
		addContent("\n<" + sessionName.toUpperCase() + ">\n");
		
		this.updated = false;
	}
	
	public void createNewSession(String sessionName, Date sessionDate) {
		SimpleDateFormat format = new SimpleDateFormat("[dd/MM/yyyy HH:mm]");
		createNewSession(sessionName + " " + format.format(sessionDate));
	}
	
	public void addMessage(String message) {
		addMessage(message, INFORMATION_OBJET);
	}
	
	public void addMessage(String message, String objet) {
		//Mise � jour des marqueurs
		if (WARNING_OBJET.equals(objet)) {
			this.hasWarning = true;
		}
		else if (ERREUR_OBJET.equals(objet)) {
			this.hasError = true;
		}
		
		//Ajout du message
		SimpleDateFormat format = new SimpleDateFormat("[dd/MM/yyyy HH:mm]");
		String time = format.format(new Date());
		
		objet = "[" + objet + "]";
		
		message = time + objet + " : " + message;
		
		addContent(message);
		this.updated = false;
	}
	
	public void addErrorMessage(String message, Throwable error) {
		String errorStr = "";
		if (error != null) {
			errorStr = this.parser.parseException(error);
			errorStr = "\t" + errorStr.replace("\n", "\t\n");
		}
		
		addMessage(message + "\n" + errorStr, ERREUR_OBJET);
		
		this.updated = false;
	}
	
	private void addContent(String message) {
		Util.addAll(message.split("\n"), this.contents, false);
		dispatchEntry(message);
	}
	
	/**
	 * Permet de savoir si une modification du Log dans l'application
	 * est survenue depuis le chargement du Log, depuis le fichier lui
	 * correspondant sur le disque dur.
	 * <p>Autrement dit, on peut savoir si le fichier du disque dur est
	 * identique au Log dans l'application.
	 * @return {@code true} si le fichier a �t� mis � jour pour toutes
	 * les modifications du Log, {@code false} si les derni�res mises �
	 * jour de l'objet n'ont pas �t� r�percut�es dans le fichier.
	 */
	public boolean isFileUpdated() {
		return this.updated;
	}
	
	/** Indique si le Log contient des messages d'erreur. */
	public boolean hasError() {
		return this.hasError;
	}
	
	/** Indique si le Log contient des messages d'alerte. */
	public boolean hasWarning() {
		return this.hasWarning;
	}
	
	public void addLogListener(LogListener l) {
		if (l == null) throw new NullPointerException("l == null");
		this.listeners.add(l);
	}
	
	public void removeAllLogListeners() {
		this.listeners.clear();
	}
	
	public LogListener[] getAllLogListeners() {
		return this.listeners.toArray(new LogListener[0]);
	}
	
	/** Envoie l'entr�e pass�e en param�tres � tous les {@link LogListener} �coutant
	 * ce Log. */
	private void dispatchEntry(String entry) {
		for (LogListener l : this.listeners) {
			l.logEntry(entry, this);
		}
	}
	
	/** Si le param�tre vaut <tt>true</tt> alors les entr�es dans le log seront
	 * �galement envoy�es � la sortie standart.
	 * @see System#out */
	public void displayLogEntries(boolean b) {
		this.listeners.remove(SYSOUT_LISTENER);
		if (b) {
			this.listeners.add(SYSOUT_LISTENER);
		}
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof Log)) return false;
		if (other == this) return true;
		
		if (this.file == null) return false;
		
		return this.file.equals(((Log) other).file);
	}
}
