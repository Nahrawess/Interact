package com.socialnetwork;
import java.util.Scanner;
import java.sql.*;
import java.util.*;

public class compte
{
	String email;
	String password;
    compte a;

  public void inviter(String e) 
    {
      try 
      {	
    	System.out.println("Email de la personne à inviter :" );
		try (Scanner sc = new Scanner(System.in)) 
		 {
			String s = sc.nextLine();
			System.out.println(s);
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialNetwork", "root" , "root");
			Statement stmt = con.createStatement();
			String sql = "insert into invitations (email,emailAttente) values ('"+ e +"','"+ s + "')";
			stmt.executeUpdate(sql);
		 }
			System.out.println("Vous êtes dans la liste d'attente");
	  }
		catch (Exception exc) 
         {
			exc.printStackTrace();
	     } 

    }

  
    public void consulterInvitations(String e) 
    {
    	try 
        {	
      	System.out.println("Voilà votre liste des amis en attente :" );
      	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialNetwork", "root" , "root");
    	PreparedStatement recherche = con.prepareStatement("SELECT emailAttente FROM invitations WHERE email = ?");
        recherche.setString(1,e);
        ResultSet resultat = recherche.executeQuery(); 
    	while(resultat.next())
    	{ 
    		String emailAttente = resultat.getString(1); 
    		System.out.println(emailAttente); 
    	}
    	}
  		catch (Exception exc) 
           {
  			exc.printStackTrace();
  	       } 
    	
    	System.out.println("Taper \n 1. Si vous voulez acceptez quelqu'un \n 2. Si vous voulez refuser quelqu'un" );
		Scanner sc = new Scanner(System.in);
    	int choice = sc.nextInt();
		switch(choice) 
		{
		  case 1:
			  try {	
				    sc.nextLine();
					System.out.println("Donner l'adresse de la personne que vous voulez accepter :" );
					String adresse1 = sc.nextLine();
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialNetwork", "root" , "root");
					Statement statement1 = con.createStatement();
		            String sql1 = "insert into liste_d_amis (email,emailAmi) values ('"+ e +"','"+ adresse1 + "');";
			        statement1.executeUpdate(sql1); 
			        
					Statement statement3 = con.createStatement();
                    String sql3 = "delete from invitations where emailAttente = ' "+adresse1+" '; ";
			        statement3.executeUpdate(sql3);
			   
			        System.out.println("Voilà votre nouvelle liste d'amis :");
			        PreparedStatement recherche = con.prepareStatement("SELECT emailAmi FROM liste_d_amis WHERE email = ?");
			        recherche.setString(1,e);
			        ResultSet res1 = recherche.executeQuery(); 
			        while(res1.next())
			    	{ 
			    		String emailAmi = res1.getString(1); 
			    		System.out.println(emailAmi); 
			    	}
			  }
			  catch (Exception exc) 
	          {
		          exc.printStackTrace();
		      }

			    break;
		  case 2:
			  try {	
				    System.out.println("Donner l'adresse de la personne que vous voulez refuser :" );
					String adresse2 = sc.nextLine();
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialNetwork", "root" , "root");
			        PreparedStatement rech4 = con.prepareStatement("delete from invitations (email,emailAttente)  where emailAttente = ?");
			        rech4.setString(1,adresse2);
			       
			        System.out.println("Voilà votre nouvelle liste d'amis en attente :");
			        PreparedStatement rech2 = con.prepareStatement("SELECT emailAttente FROM invitations WHERE email = ?");
			        rech2.setString(1,e);
			        ResultSet res2 = rech2.executeQuery(); 
			     	while(res2.next())
			     	{ 
			     		String emailAttente = res2.getString(1); 
			     		System.out.println(emailAttente); 
			     	}
			       } 
			  
			  catch (Exception exc) 
	          {
		          exc.printStackTrace();
		      }

			    break;
		  
	      default :
	    	  System.out.printf("Retour au menu principal"
	    	  		+ "");

		  break;	
		
}
   
}
    
    
    
    public void publierStatut(String e) 
    { 

      	try 
  	{	
		Scanner sc = new Scanner(System.in);
		System.out.println("Statut :" );
		String s = sc.nextLine();
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialNetwork", "root" , "root");
		Statement statement1 = con.createStatement();
        String sql1 = "insert into mur (email,statut) values ('"+ e +"','"+ s + "');";
        statement1.executeUpdate(sql1); 
		 }
	  
		catch (Exception exc) 
       {
			exc.printStackTrace();
	     } 

    }
    
    public void effacerStatut(String e) 
    {
    	try 
      	{	
    		Scanner sc = new Scanner(System.in);
			System.out.println("Donner le statut à effacer :" );
			String s = sc.nextLine();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialNetwork", "root" , "root");
			Statement statement = con.createStatement();
            String sql = "delete from mur where statut = ' "+s+" '; ";
	        statement.executeUpdate(sql);
  		}
  	  
  		catch (Exception exc) 
         {
  			exc.printStackTrace();
  	     } 

    }
    
    
    public void commenterStatut(String e) 
    {
    	try 
      	{	
    		Scanner sc = new Scanner(System.in);
			System.out.println("Donner le statut à commenter:" );
			String s = sc.nextLine();
			System.out.println("Donner le admin du statut :" );
			String a = sc.nextLine();
			System.out.println("Commentaire:" );
			String c = sc.nextLine();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialNetwork", "root" , "root");
			Statement statement1 = con.createStatement();
            String sql1 = "insert into commentaires (emailProp,emailComm,statut,commentaire) values ('"+ a +"','"+ e + "','"+ s + "','"+c+"');";
	        statement1.executeUpdate(sql1); 
  		 }
  	  
  		catch (Exception exc) 
           {
  			exc.printStackTrace();
  	     } 

    }
    
    
    public void envoyerMessage(String e) 
    {

      	try 
  	{	
		Scanner sc = new Scanner(System.in);
		System.out.println("Message à envoyer:" );
		String msg = sc.nextLine();
		System.out.println("Votre destinataire:" );
		String d = sc.nextLine();
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialNetwork", "root" , "root");
		Statement statement1 = con.createStatement();
        String sql1 = "insert into messages (expediteur,destinataire,message) values ('"+ e +"','"+ d + "','"+ msg + "');";
        statement1.executeUpdate(sql1); 
		 }
	  
		catch (Exception exc) 
       {
			exc.printStackTrace();
	     } 

    }
    
    
    public void voirMessage(String e) 
    {
    	try 
      	{	
    		System.out.println("Vos messages : \n");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialNetwork", "root" , "root");
            PreparedStatement rech2 = con.prepareStatement("SELECT expediteur,message FROM messages WHERE destinataire = ?");
	        rech2.setString(1,e);
	        ResultSet res2 = rech2.executeQuery(); 
	     	while(res2.next())
	     	{ 
	     		String exp = res2.getString(1); 
	     		String message = res2.getString(2); 

	     		System.out.format("Message de %s : %s \n",exp,message); 
	     	}
	     }
  	  
  		catch (Exception exc) 
         {
  			exc.printStackTrace();
  	     }

        
    }
   
    
    public void creerPage(String e) 
    {
        try 
        {
        	Scanner sc = new Scanner(System.in);
        
		System.out.println("Saisir le nom de la page : " );
		String nom = sc.nextLine();
		System.out.println("Saisir son genre : " );
		String genre = sc.nextLine();
		System.out.println("Description : " );
		String description = sc.nextLine();
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialNetwork", "root" , "root");
        Statement statement = con.createStatement();
        String sql = "insert into pages (nomP,genreP,admin,description) values ('" + nom + "','" + genre +"','" + e +"','" + description +"');";
        statement.executeUpdate(sql);
       }
        catch (Exception exc) 
        {
 			exc.printStackTrace();
 	    } 

		
    }

    
    public void likePage(String e) 
    {
    	try 
      	{			
    		Scanner sc = new Scanner(System.in);
    		System.out.println("Les pages : \n");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialNetwork", "root" , "root");
            PreparedStatement rech2 = con.prepareStatement("SELECT nomP,admin FROM pages");
	        ResultSet res2 = rech2.executeQuery(); 
	     	while(res2.next())
	     	{ 
	     		String nom = res2.getString(1); 
	     		System.out.format(nom); 
	     		System.out.println("\n"); 

	     	}
	     	System.out.println("Nom de la page que vous voulez aimer:" );
			String nom = sc.nextLine();
			Statement statement1 = con.createStatement();
	        String sql1 = "insert into fans (nomFan,nomPage) values ('"+ e +"','"+ nom + "');";
	        statement1.executeUpdate(sql1); 
	     	
  		 }
    	catch (Exception exc) 
         {
  			exc.printStackTrace();
  	     }   

        
    }

    
    public void dislikePage(String e) 
    {
    	try 
      	{			
    		Scanner sc = new Scanner(System.in);
    		System.out.println("Les pages : \n");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialNetwork", "root" , "root");
            PreparedStatement rech2 = con.prepareStatement("SELECT nomP,admin FROM pages");
	        ResultSet res2 = rech2.executeQuery(); 
	     	while(res2.next())
	     	{ 
	     		String nom = res2.getString(1); 
	     		System.out.format(nom); 
	     		System.out.println("\n"); 

	     	}
	     	System.out.println("Nom de la page que vous voulez disliker:" );
			String nom = sc.nextLine();
			Statement statement1 = con.createStatement();
	        String sql1 = "delete from fans where nomPage='"+nom+"' ;";
	        statement1.executeUpdate(sql1); 
	     	
  		 }
    	catch (Exception exc) 
         {
  			exc.printStackTrace();
  	     }  

    }
    
    
    
    public void creerGroupe(String e) 
    {
    	try 
        {
        	Scanner sc = new Scanner(System.in);
        
		System.out.println("Saisir le nom du groupe : " );
		String nom = sc.nextLine();
		System.out.println("Saisir son genre : " );
		String genre = sc.nextLine();
		System.out.println("Description : " );
		String description = sc.nextLine();
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialNetwork", "root" , "root");
        Statement statement = con.createStatement();
        String sql = "insert into groupes (nomG,genreG,admin,description) values ('" + nom + "','" + genre +"','" + e +"','" + description +"');";
        statement.executeUpdate(sql);
       }
        catch (Exception exc) 
        {
 			exc.printStackTrace();
 	    } 

    }

    
    public void rejoindreGroupe(String e) 
    {
    	try 
      	{			
    		Scanner sc = new Scanner(System.in);
    		System.out.println("Les groupes : \n");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialNetwork", "root" , "root");
            PreparedStatement rech2 = con.prepareStatement("SELECT nomG,admin FROM groupes");
	        ResultSet res2 = rech2.executeQuery(); 
	     	while(res2.next())
	     	{ 
	     		String nom = res2.getString(1); 
	     		System.out.format(nom); 
	     		System.out.println("\n"); 

	     	}
	     	System.out.println("Nom du groupe que vous voulez rejoindre:" );
			String nom = sc.nextLine();
			Statement statement1 = con.createStatement();
	        String sql1 = "insert into membresGroupe (nomMembre,nomGroupe) values ('"+ e +"','"+ nom + "');";
	        statement1.executeUpdate(sql1); 
	     	
  		 }
    	catch (Exception exc) 
         {
  			exc.printStackTrace();
  	     }  

        
    }

    
    public void quitterGroupe(String e) 
    {
    	try 
      	{			
    		Scanner sc = new Scanner(System.in);
    		System.out.println("Les groupes : \n");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialNetwork", "root" , "root");
            PreparedStatement rech2 = con.prepareStatement("SELECT nomG,admin FROM groupes");
	        ResultSet res2 = rech2.executeQuery(); 
	     	while(res2.next())
	     	{ 
	     		String nom = res2.getString(1); 
	     		System.out.format(nom); 
	     		System.out.println("\n"); 

	     	}
	     	System.out.println("Nom du groupe que vous voulez quitter:" );
			String nom = sc.nextLine();
			Statement statement1 = con.createStatement();
	        String sql1 = "delete from membresGroupe where nomGroupe='"+nom+"';";
	        statement1.executeUpdate(sql1); 
	     	
  		 }
    	catch (Exception exc) 
         {
  			exc.printStackTrace();
  	     }  
    }

    public void suggestionD_amis(String e) 
    {
    	try 
      	{	
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialNetwork", "root" , "root");
        PreparedStatement rech1 = con.prepareStatement("SELECT emailAmi FROM liste_d_amis where email=?");
        rech1.setString(1,e);
        ResultSet res1 = rech1.executeQuery(); 
        PreparedStatement rech2 = con.prepareStatement("SELECT email FROM compte");
        ResultSet res2 = rech2.executeQuery();
        while(res2.next())
    	{ 
    		String emailAmi = res1.getString(1); 
    		String email = res2.getString(1); 
    	
    	if (emailAmi.equals(email)) 
    	{
    		break;
    	} 
    	else 
    	{
    		System.out.println("On vous suggère: "); 
    		System.out.println(emailAmi);
    		System.out.println("\n"); 
    	}
        }
      	}
    	catch (Exception exc) 
        {
 			exc.printStackTrace();
 	     }  
    }
    
    public void seDeconnecter() 
    {
    	try 
      	{			
    		
  		}
    	catch (Exception exc) 
        {
  			exc.printStackTrace();
  	    }                
    }
   }
