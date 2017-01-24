package fi.softala.jee.demo.d10.dao.webuser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fi.softala.jee.demo.d10.bean.WebUser;
import fi.softala.jee.demo.d10.dao.DAO;
import fi.softala.jee.demo.d10.dao.DAOPoikkeus;

public class WebUserDAO extends DAO {

	public WebUserDAO() throws DAOPoikkeus {
		super();
	}

	/**
	 * Lisää uuden webuserin tietokantaan
	 * 
	 * @param kayttaja
	 *            uuden webuserin tiedot
	 * @throws UsernameVarattuPoikkeus
	 *             Mikäli tietokannasta löytyy jo käyttäjä samalla usernamella
	 * @throws DAOPoikkeus
	 *             Mikäli tietokantahaussa tapahtuu virhe
	 */
	public void lisaa(WebUser kayttaja) throws UsernameVarattuPoikkeus,
			DAOPoikkeus {
		Connection yhteys = avaaYhteys();

		try {

			// tarkistetaan, että usernamella ei jo löydy käyttäjää
			PreparedStatement usernameHaku = yhteys
					.prepareStatement("select id from webuser where username = ?");
			usernameHaku.setString(1, kayttaja.getUsername());
			ResultSet rs = usernameHaku.executeQuery();
			if (rs.next())
				throw new UsernameVarattuPoikkeus();

			// suoritetaan lisäys
			PreparedStatement insertLause = yhteys
					.prepareStatement("insert into webuser(username, password_hash, salt) values(?,?,?)");
			insertLause.setString(1, kayttaja.getUsername());
			insertLause.setString(2, kayttaja.getPasswordHash());
			insertLause.setString(3, kayttaja.getSalt());

			insertLause.executeUpdate();

		} catch (SQLException e) {
			// JOTAIN VIRHETTÄ TAPAHTUI
			throw new DAOPoikkeus("Tietokantahaku aiheutti virheen", e);
		} finally {
			// LOPULTA AINA SULJETAAN YHTEYS
			suljeYhteys(yhteys);
		}

	}

	public WebUser hae(String username) throws DAOPoikkeus {
		WebUser kayttaja;
		Connection yhteys = avaaYhteys();

		try {

			// tarkistetaan, että usernamella ei jo löydy käyttäjää
			PreparedStatement usernameHaku = yhteys
					.prepareStatement("select id, username, salt, password_hash, demo, pisteet from webuser where username = ?");
			usernameHaku.setString(1, username);
			ResultSet rs = usernameHaku.executeQuery();
			if (rs.next()) {
				// LÖYTYI
				kayttaja = new WebUser(rs.getInt("id"),
						rs.getString("username"), rs.getString("salt"),
						rs.getString("password_hash"), rs.getString("demo"),
						rs.getString("pisteet"));
			} else {
				// EI LÖYTYNYT
				// generoidaan kuitenkin tyhjä user, jotta
				// login tarkistus kestää aina yhtä kauan
				kayttaja = new WebUser(-1, "-", "-", "-");
			}

		} catch (SQLException e) {
			// JOTAIN VIRHETTÄ TAPAHTUI
			throw new DAOPoikkeus("Tietokantahaku aiheutti virheen", e);
		} finally {
			// LOPULTA AINA SULJETAAN YHTEYS
			suljeYhteys(yhteys);
		}
		return kayttaja;
	}

	public void lisaaDemo(String demo, String username) throws DAOPoikkeus {

		Connection yhteys = avaaYhteys();

		try {

			// suoritetaan haku

			// alustetaan sql-lause
			String sql = "update webuser set demo=? where username= '"
					+ username + "'";
			PreparedStatement lause = yhteys.prepareStatement(sql);

			// täytetään puuttuvat tiedot
			lause.setString(1, demo);

			// suoritetaan lause
			lause.executeUpdate();
			System.out.println("LISÄTTIIN DEMO TIETOKANTAAN");
		} catch (Exception e) {
			// JOTAIN VIRHETTÄ TAPAHTUI
			e.printStackTrace();
		} finally {
			// LOPULTA AINA SULJETAAN YHTEYS
			suljeYhteys(yhteys);
		}

	}
	
	public List<WebUser> haeKaikki() throws DAOPoikkeus {

		ArrayList<WebUser> kayttajat = new ArrayList<WebUser>();

		// avataan yhteys
		Connection yhteys = avaaYhteys();

		try {

			// suoritetaan haku
			String sql = "select id, username, password_hash, salt, demo, pisteet from webuser";
			Statement haku = yhteys.createStatement();
			ResultSet tulokset = haku.executeQuery(sql);

			// käydään hakutulokset läpi
			while (tulokset.next()) {
				String username = tulokset.getString("username");
				String password_hash = tulokset.getString("password_hash");
				String salt = tulokset.getString("salt");
				String demo = tulokset.getString("demo");
				String pisteet = tulokset.getString("pisteet");

				// lisätään henkilö listaan
				
				if (!username.equals("admin")){
				WebUser w = new WebUser(username, password_hash, salt, demo, pisteet);
				kayttajat.add(w);
				}
			}

		} catch (Exception e) {
			// JOTAIN VIRHETTÄ TAPAHTUI
			throw new DAOPoikkeus("Tietokantahaku aiheutti virheen", e);
		} finally {
			// LOPULTA AINA SULJETAAN YHTEYS
			suljeYhteys(yhteys);
		}

		System.out.println("HAETTIIN TIETOKANNASTA KÄYTTÄJÄT: "
				+ kayttajat.toString());

		return kayttajat;
	}
	
	public void paivitaPisteet(String arvioitava, String pisteet) throws DAOPoikkeus {

		Connection yhteys = avaaYhteys();

		try {

			// suoritetaan haku

			// alustetaan sql-lause
			String sql = "update webuser set pisteet=? where username='" + arvioitava + "'";
			PreparedStatement lause = yhteys.prepareStatement(sql);

			// täytetään puuttuvat tiedot
			lause.setString(1, pisteet);
			
			// suoritetaan lause
			lause.executeUpdate();
			System.out.println("PÄIVITETTIIN PISTEET TIETOKANTAAN: " + arvioitava);
		} catch (Exception e) {
			// JOTAIN VIRHETTÄ TAPAHTUI
			throw new DAOPoikkeus("Päivitys aiheutti virheen", e);
		} finally {
			// LOPULTA AINA SULJETAAN YHTEYS
			suljeYhteys(yhteys);
		}

	}

}
