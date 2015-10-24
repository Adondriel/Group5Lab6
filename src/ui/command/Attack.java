package ui.command;

import Exceptions.EnvironmentException;
import environment.Environment;
import lifeform.LifeForm;
import environment.Environment;
/**
 * 
 * @author Bradley Solorzano, Benjamin Uleau
 *	Design Pattners Group 5 lab 6
 */
public class Attack implements Command
{
	private LifeForm entity;
	
	Environment e = Environment.getWorldInstance(5, 5);
	private char north='n';
	private char south='s';
	private char east='e';
	private char west='w';
	public void attackCommand(int row, int col) throws EnvironmentException
	{
		int attacked=0;
		entity=e.getLifeForm(row, col);
		
		//Attack north
		if(entity.getDirection()==north){
			for(int i=row; i>=0; i--){
				if(e.getLifeForm(i, col)!=null && attacked==0 && i!=row){
					LifeForm lf=e.getLifeForm(i, col);
					entity.attackLF(e.getLifeForm(i, col));
					attacked++;
				}
			}
		}
		
		//Attack south
		if(entity.getDirection()==south){
			for(int i=row; i<e.getMaxRow(); i++){
				if(e.getLifeForm(i, col)!=null && attacked==0 && i!=row){
					LifeForm lf=e.getLifeForm(i, col);
					entity.attackLF(lf);
					attacked++;
				}
			}
		}
		
		//Attack east
		if(entity.getDirection()==east){
			for(int i=col; i<e.getMaxCol(); i++){
				if(e.getLifeForm(row, i)!=null && attacked==0 && i!=col){
					LifeForm lf=e.getLifeForm(row, i);
					entity.attackLF(lf);
					attacked++;
				}
			}
		}
		
		//Attack west
		if(entity.getDirection()==west){
			for(int i=col; i>=0; i--){
				if(e.getLifeForm(row, i)!=null && attacked==0 && i!=col){
					LifeForm lf=e.getLifeForm(row, i);
					entity.attackLF(lf);
					attacked++;
				}
			}
		}
	}
	@Override
	public void execute(LifeForm lf)
	{
		try {
			attackCommand(lf.getMyRow(), lf.getMyCol());
		} catch (EnvironmentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
