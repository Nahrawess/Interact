package com.socialnetwork;
import java.sql.*;
import java.util.Scanner;
public class connection 
{
	public static void main(String[] args) throws SQLException
		{
		compte a = new compte();
		Scanner sc = new Scanner(System.in);
	    System.out.println("Bienvenue à Interact ! \n 1. S'identifier \n 2. S'inscrire \n");
	    int i = sc.nextInt();
	    if (i==1) 
	    {  
	    	try 
	      	{		
	    String adresse ="";
	    String mdp = "";    

	                System.out.print( "Donner votre adresse Email : " );
	                sc.nextLine();
	                String email = sc.nextLine();
	                System.out.print( "Donner votre mot de passe :" );
	                String password = sc.nextLine();
	                
	                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialNetwork", "root" , "root");
	            	Statement stmt = conn.createStatement();
	            	String sql = "SELECT email,password FROM compte where email ='"+email+"' and password ='" +password+"'";
	            	ResultSet res = stmt.executeQuery(sql);
	            	while(res.next())
	                {
	                	
	                      adresse = res.getString("email");
	                      
	                      mdp = res.getString("password");
	                      if ((adresse.equals("")== false) && (mdp.equals("") == false)) 
	                      {
	             			 System.out.println ("\nSoyez le bienvenu \n");
	                      
	            	      }
	                }
	    	    	a.email=adresse;
	    	    	a.password=mdp;

	      	}
	    	
	        
	    	catch (Exception exc) 
	        {
	 			exc.printStackTrace();
	 	     }          
	    	   try 
	              	{	
	            		System.out.println("Votre mur : \n");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialNetwork", "root" , "root");
                        PreparedStatement rech2 = con.prepareStatement("SELECT statut FROM mur WHERE email = ?");
				        rech2.setString(1,a.email);
				        ResultSet res2 = rech2.executeQuery(); 
				     	while(res2.next())
				     	{ 
				     		String Statut = res2.getString(1); 
				     		System.out.format("Statut : %s \n",Statut); 
				     		PreparedStatement rech = con.prepareStatement("SELECT emailComm,commentaire FROM commentaires where emailProp= ? and statut=?");
					        rech.setString(1,a.email);
					        rech.setString(2,Statut);
                            ResultSet res1 = rech.executeQuery(); 
					     	while(res1.next())
					     	{   String emailComm = res1.getString(1);
					     	    String Comm = res1.getString(2);
					     	System.out.format("  Commentaire de %s: %s \n",emailComm,Comm);
					     	}
				     		
				     	}
	          		 }
	          	  
	          		catch (Exception exc) 
	                 {
	          			exc.printStackTrace();
	          	     }             
	                
	            	try 
	              	{	
	            		System.out.println("Fil d'actualité : \n");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialNetwork", "root" , "root");
                        PreparedStatement rech2 = con.prepareStatement("SELECT * FROM mur where email <> ?");
				        rech2.setString(1,a.email);
				        ResultSet res2 = rech2.executeQuery(); 
				     	while(res2.next())
				     	{   String Email = res2.getString(1);
				     		String Statut = res2.getString(2); 
				     		System.out.format("Statut de %s: %s \n",Email,Statut); 
				     		PreparedStatement rech = con.prepareStatement("SELECT emailComm,commentaire FROM commentaires where emailProp= ? and statut=?");
					        rech.setString(1,Email);
					        rech.setString(2,Statut);
                            ResultSet res1 = rech.executeQuery(); 
					     	while(res1.next())
					     	{   
					     	String emailComm = res1.getString(1);
					     	String Comm = res1.getString(2);
					     		System.out.format("  Commentaire de %s: %s \n",emailComm,Comm); 
					     		System.out.println("\n");
					     	}
				     	}
	          		 }
	          	  
	          		catch (Exception exc) 
	                 {
	          			exc.printStackTrace();
	          	     }             
	                
	            /*Suggestion des amis
	            	a.SuggestionD_Amis(a.email);	*/
	            	System.out.println("Menu Principal:\n"
	            			+ "1. Consulter les invitations \n"
	            			+ "2. Publier un statut \n"
	            			+ "3. Effacer un statut \n"
	            			+ "4. Commenter un statut \n"
	            			+ "5. Envoyer une invitation \n"
	            			+ "6. Envoyer un message \n"
	            			+ "7. Voir vos messages \n"
	            			+ "8. Créer une page \n"
	            			+ "9. Like Page \n"
	            			+ "10.Dislike Page\n"
	            			+ "11.Créer un Groupe \n"
	            			+ "12.Rejoindre un Groupe \n"
	            			+ "13.Quitter un Groupe \n"
	            			+ "14.Se déconnecter;");
	                try 
	                {
	              	int choix = sc.nextInt();
	              	switch(choix) 
	          		{
	          		  case 1:
	          			    a.consulterInvitations(a.email);
	          			    break;
	          		  case 2:
	          			    a.publierStatut(a.email);
	          			    break;
	          		  case 3:
	          				a.effacerStatut(a.email);
	          			    break;
	          		  case 4:
	          				a.commenterStatut(a.email);
	          			    break;
	          		  case 5 :
	          			    a.suggestionD_amis(a.email);
	          			    a.inviter(a.email);
	          			    break;
	          		  case 6 :
	          				a.envoyerMessage(a.email);
	          			    break;
	          		  case 7 :
	          				a.voirMessage(a.email);
	          			    break;
	          		  case 8 :
	          				a.creerPage(a.email);
	          			    break;
	          		  case 9 :
	          			  a.likePage(a.email);
	          			    break;
	          		  case 10 :
	          			  a.dislikePage(a.email);
	          			    break;
	          		  case 11 :
	          			  a.creerGroupe(a.email);
	          			    break;
	          		  case 12 :
	          			  a.rejoindreGroupe(a.email);
	          			    break;
	          		  case 13 :
	          			  a.quitterGroupe(a.email);
	          			    break;
	          		  case 14 : 
	          			  a.seDeconnecter();
	          			  break;
	          	      default :
	          	    	  System.out.printf("Il faut choisir une option");
	          		  break;	

	        	  }
	              }
	        		catch (Exception exc) 
	                 {
	        			exc.printStackTrace();
	        	     } 
  
	    }
	    
        	
		else /* s'inscrire*/
		{
			System.out.println("Il faut s'inscrire \n");
			System.out.println("Adresse email : " );
			sc.nextLine();
		    String email = sc.nextLine();

			
			System.out.println("Prénom : " );
			String prenom = sc.nextLine();
			
			System.out.println("Nom : " );
			String nom = sc.nextLine();
			
			System.out.println("Saisir votre mot de passe " );
			String mdp = sc.nextLine();
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialNetwork", "root" , "root");
			Statement stmt = conn.createStatement();
		    String sql1 = "insert into utilisateurs (email,prenom,nom) values ('" + email + "','" + prenom +"','" + nom + "')";
		    stmt.executeUpdate(sql1);
		    String sql2 = "insert into compte (email,password) values ('" + email + "','" + mdp +"')";
		    stmt.executeUpdate(sql2);
			System.out.println ("Félicitations, vous êtes inscrits!");
			a.email=email;
		    a.password=mdp;
		}
	    
}
}
	