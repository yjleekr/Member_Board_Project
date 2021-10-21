package Database;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import Controller.MemberController;
import Model.Member;

public class File {
//�ʵ�
	// 1. ȸ�������� �����ϴ� ������ ���
	
	private static String memberpath = "D:/2021.09.28/Member_Board_Project/src/Database/Memberlist.txt";
	// �޼ҵ�
	public static boolean filesave(int type) {
		try {				
			FileOutputStream fileOutputStream = null; //������
			if( type == 1 ) { // ȸ������ 
				// 1. ���ϰ�ü�� ��� ���� 
				fileOutputStream = new FileOutputStream( memberpath );
				// 2. �ݺ����� �̿��� ȸ������Ʈ���� �ϳ��� ȸ�� ��������
				for( Member member : MemberController.memberlist ) {
					// 3. �� ȸ������ �ʵ�[,]�� ȸ��[/n] ����
					String outstring = member.getId()+","+member.getPassword()+","+
										member.getName()+","+member.getEmail()+","+
										member.getPoint()+"\n";
					// 4. ����Ʈ�� �������� 
					fileOutputStream.write( outstring.getBytes() );
				}
				// 5. ��Ʈ�� ����� �ʱ�ȭ
				fileOutputStream.flush(); // ���Ͻ�Ʈ���� �����ϴ� ����Ʈ ���� 
				fileOutputStream.close(); // ���Ͻ�Ʈ�� �ݱ�
				
				return true; // ����ó�� ����
			}
			if( type == 2 ) {}
			if( type == 3 ) {}
			
	
		}catch (Exception e) {
			System.out.println(" [�˸�] : ���� ���� ���� �߻� [ �����ڿ��� ���� ]");
		}
		return false; // ����ó�� ����
	}
	
	// �ҷ����� �޼ҵ�
	public static boolean fileload( int type ) {
		try { // ����ó�� �ϴ����� ?? 
			FileInputStream fileInputStream = null;
			if( type == 1 ) {
				// 1. �Է½�Ʈ�� ��� ���� 
				fileInputStream = new FileInputStream(memberpath);
				// 2. ��Ʈ��(����:����Ʈ) ����Ʈ�迭 ���� 
				byte[] bytes = new byte[10000]; // 10kb ����
				// 3. �Է½�Ʈ������ ����Ʈ �о�ͼ� �迭�� ���� 
				fileInputStream.read( bytes );
				// 4. ����Ʈ�迭 -> ���ڿ� ��ȯ
				String instring = new String(bytes);
				// 5. ȸ�� �и��ϱ� \n
				String[] members = instring.split("\n"); // \n ���н� ����ȸ�� �߰� 
				// 6. �ݺ����� �̿��� ȸ���� �ʵ� �и��ϱ� ,
				for( int i = 0 ; i<members.length-1 ;i++ ) { // -1 : ����ȸ�� ����
					// 7. ȸ���� �ʵ� �и� 	
					String[] field = members[i].split(",");
					// 8. �и��� �ʵ带 ��üȭ [ point�ʵ�� int������ ��ȯ : String -> Int ( Integer.parseInt )  ] 
					Member member = new Member( field[0] , field[1] ,  
									field[2], field[3],  
									Integer.parseInt(field[4] ));
					// 9. �� ��ü�� ����Ʈ�� ����
					MemberController.memberlist.add(member);
				}
				fileInputStream.close(); // ��Ʈ�� �ݱ� 
				return true; // ���� �ҷ����� ����
			}
			if( type == 2 ) {}
			if( type == 3 ) {}
		}
		catch (Exception e) {
			System.out.println(" [�˸�] : ���� �ҷ����� ���� �߻� [ �����ڿ��� ���� ]");
		}
		return false; // ���� �������� ���н�
	}
	

	
}