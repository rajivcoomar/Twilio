package com.rajivcoomar.ivrtest.readthemanual.model;

import com.twilio.twiml.voice.Say;

public class IVRGetLocale {
	

static public Say.Language getLocal(String locale)
{
	
	Say.Language  returnValue;
	
	if(locale.equalsIgnoreCase("en-us"))
	{
		returnValue = Say.Language.EN_US;
	}
	else if(locale.equalsIgnoreCase("da-DK"))
	{
		returnValue = Say.Language.DA_DK;
	}
	else if(locale.equalsIgnoreCase("de-DE"))
		{
			returnValue = Say.Language.DE_DE;
		}
	else if(locale.equalsIgnoreCase("en-AU"))
		{
			returnValue = Say.Language.EN_AU;
		}
	else if(locale.equalsIgnoreCase("en-CA"))
		{
			returnValue = Say.Language.EN_CA;
		}
	else if(locale.equalsIgnoreCase("en-GB"))
		{
			returnValue = Say.Language.EN_GB;
		}
	else if(locale.equalsIgnoreCase("en-IN"))
		{
			returnValue = Say.Language.EN_IN;
		}
	else if(locale.equalsIgnoreCase("en-US"))
		{
			returnValue = Say.Language.EN_US;
		}
	else if(locale.equalsIgnoreCase("ca-ES"))
		{
			returnValue = Say.Language.CA_ES;
		}
	else if(locale.equalsIgnoreCase("es-ES"))
		{
			returnValue = Say.Language.ES_ES;
		}
	else if(locale.equalsIgnoreCase("es-MX"))
		{
			returnValue = Say.Language.ES_MX;
		}
	else if(locale.equalsIgnoreCase("fi-FI"))
		{
			returnValue = Say.Language.FI_FI;
		}
	else if(locale.equalsIgnoreCase("fr-CA"))
		{
			returnValue = Say.Language.FR_CA;
		}
	else if(locale.equalsIgnoreCase("fr-FR"))
		{
			returnValue = Say.Language.FR_FR;
		}
	else if(locale.equalsIgnoreCase("it-IT"))
		{
			returnValue = Say.Language.IT_IT;
		}
	else if(locale.equalsIgnoreCase("ja-JP"))
		{
			returnValue = Say.Language.JA_JP;
		}
	else if(locale.equalsIgnoreCase("ko-KR"))
		{
			returnValue = Say.Language.KO_KR;
		}
	else if(locale.equalsIgnoreCase("nb-NO"))
		{
			returnValue = Say.Language.NB_NO;
		}
	else if(locale.equalsIgnoreCase("nl-NL"))
		{
			returnValue = Say.Language.NL_NL;
		}
	else if(locale.equalsIgnoreCase("pl-PL"))
		{
			returnValue = Say.Language.PL_PL;
		}
	else if(locale.equalsIgnoreCase("pt-BR"))
		{
			returnValue = Say.Language.PT_BR;
		}
	else if(locale.equalsIgnoreCase("pt-PT"))
		{
			returnValue = Say.Language.PT_PT;
		}
	else if(locale.equalsIgnoreCase("ru-RU"))
		{
			returnValue = Say.Language.RU_RU;
		}
	else if(locale.equalsIgnoreCase("sv-SE"))
		{
			returnValue = Say.Language.SV_SE;
		}
	else if(locale.equalsIgnoreCase("zh-CN"))
		{
			returnValue = Say.Language.ZH_CN;
		}
	else if(locale.equalsIgnoreCase("zh-HK"))
		{
			returnValue = Say.Language.ZH_HK;
		}
	else if(locale.equalsIgnoreCase("zh-TW"))
		{
			returnValue = Say.Language.ZH_TW;
		}
	else 
	{
		returnValue = Say.Language.EN_US;
	}
	return returnValue;
}



}
