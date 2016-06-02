package br.pucgoias.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class DateConverter implements Converter {

	private DateFormat formata = new SimpleDateFormat("dd/MM/yyyy");	
	
	///Converte o objeto da jspx para gravar no banco de dados.
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {  
		
		Date data = new Date(); 
				
		
		//pegar o label do componente
		String label = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map = component.getAttributes();
		label = (String) map.get("label");		
		
		try{
			
			if (value.equals("")  || value == null || value.equals(null)){				
				return null;	//isto devolve ao jsf o controle de campos em branco (required=true/false)			
			}
			
			//isto for�a o formatador a lan�ar uma exception se a data for inv�lida
			formata.setLenient(false);
			
			data = formata.parse(value);
			
		}catch (ParseException e) { 			
			throw new ConverterException("Data inv�lida");
			
		}catch (Exception e){			
			throw new ConverterException("Data inv�lida");
		}
		
		return data;  
		
	}  
	   
  	//Converte o objeto que vem do banco de dados para ser apresentado na xhtml.
    public String getAsString(FacesContext context, UIComponent component, Object value)throws ConverterException {  
    	if(value == null) return "";  
    	String dataFormatada = null;  
    	dataFormatada = formata.format(((Date) value).getTime());  
    	return dataFormatada;  
    }


	    
}
