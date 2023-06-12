package exception;

/**
 * <h1>BadFileException Class</h1>
 * <p><b>This class is a exception for corrupted file.<b><p>
 * 
 * @author CheukLamCHUNG (s3655395)
 * @version 1.0
 * @since 2019-10-11
 */
public class BadFileException extends Exception
{
	public BadFileException (String message)
	{
		super (message);
	}

}
