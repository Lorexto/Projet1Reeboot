package fr.isika.cda22.Projet1Reeboot;

public class Admin {

	protected static String userId;
	protected static String mdp;



	public Admin(String userId, String mdp) {
		super();
		Admin.userId = userId;
		Admin.mdp = mdp;
	}


	public static String getUserId() {
		return userId;
	}


	public static void setUserId(String userId) {
		userId = userId;
	}


	public static String getMdp() {
		return mdp;
	}


	public static void setMdp(String mdp) {
		mdp = mdp;
	}









//	  public void actionPerformed(ActionEvent event)
//	   {
//	     File inputFile = new File("USERDATA.txt");
//
//	     String userIdInput = userIdInput.getText();
//	     String mdpInput = mdpInput.getText();
//
//	     try {
//	            Scanner in = new Scanner(new File("USERDATA.txt"));
//	            while (in.hasNextLine())
//	            {
//	              String s = in.nextLine();
//	              String[] sArray = s.split(",");
//
//	              System.out.println(sArray[0]); //Just to verify that file is being read
//	              System.out.println(sArray[1]);

//
//	              if (userIdInput == sArray[0] && mdpInput == sArray[1])
//	              {
//	                JOptionPane.showMessageDialog(null,
//	                    "Login Successful", "Success",
//	                    JOptionPane.INFORMATION_MESSAGE);
//	              }
//	              else
//	              {
//	                JOptionPane.showMessageDialog(null,
//	                    "Invalid Username / Password Combo", "Error",
//	                    JOptionPane.ERROR_MESSAGE);
//	              }
//	            }

//	            in.close();

//	        } catch (FileNotFoundException e) {
//	            JOptionPane.showMessageDialog(null,
//	                    "User Database Not Found", "Error",
//	                    JOptionPane.ERROR_MESSAGE);
//	        }






//
//		if (AdminList.contains(userIdInput)) { // Si userId existe dans la liste
//
//			if (this.mdp==mdpInput) {
//				return 1; // mot de passe correspond
//			}
//			else {
//				return 2;// mot de passe ne correspond pas
//			}
//		}
//			else {
//				return 3;// User id n'est pas dans la liste
//
//			}













}
