package com.rajivcoomar.ivrtest.readthemanual.model;

import com.twilio.twiml.voice.Gather;
import com.twilio.twiml.voice.Gather;

public class IVRASRGetLocale {
	

static public Gather.Language getLocal(String locale)
{
	
Gather.Language  returnValue;
	
	if(locale.equalsIgnoreCase("en-us"))
	{
		returnValue = Gather.Language.EN_US;
	}
	else if(locale.equalsIgnoreCase("da-DK"))
	{
		returnValue = Gather.Language.DA_DK;
	}
	else if(locale.equalsIgnoreCase("de-DE"))
		{
			returnValue = Gather.Language.DE_DE;
		}
	else if(locale.equalsIgnoreCase("en-AU"))
		{
			returnValue = Gather.Language.EN_AU;
		}
	else if(locale.equalsIgnoreCase("en-CA"))
		{
			returnValue = Gather.Language.EN_CA;
		}
	else if(locale.equalsIgnoreCase("en-GB"))
		{
			returnValue = Gather.Language.EN_GB;
		}
	else if(locale.equalsIgnoreCase("en-IN"))
		{
			returnValue = Gather.Language.EN_IN;
		}
	else if(locale.equalsIgnoreCase("en-US"))
		{
			returnValue = Gather.Language.EN_US;
		}
	else if(locale.equalsIgnoreCase("ca-ES"))
		{
			returnValue = Gather.Language.CA_ES;
		}
	else if(locale.equalsIgnoreCase("es-ES"))
		{
			returnValue = Gather.Language.ES_ES;
		}
	else if(locale.equalsIgnoreCase("es-MX"))
		{
			returnValue = Gather.Language.ES_MX;
		}
	else if(locale.equalsIgnoreCase("fi-FI"))
		{
			returnValue = Gather.Language.FI_FI;
		}
	else if(locale.equalsIgnoreCase("fr-CA"))
		{
			returnValue = Gather.Language.FR_CA;
		}
	else if(locale.equalsIgnoreCase("fr-FR"))
		{
			returnValue = Gather.Language.FR_FR;
		}
	else if(locale.equalsIgnoreCase("it-IT"))
		{
			returnValue = Gather.Language.IT_IT;
		}
	else if(locale.equalsIgnoreCase("ja-JP"))
		{
			returnValue = Gather.Language.JA_JP;
		}
	else if(locale.equalsIgnoreCase("ko-KR"))
		{
			returnValue = Gather.Language.KO_KR;
		}
	else if(locale.equalsIgnoreCase("nb-NO"))
		{
			returnValue = Gather.Language.NB_NO;
		}
	else if(locale.equalsIgnoreCase("nl-NL"))
		{
			returnValue = Gather.Language.NL_NL;
		}
	else if(locale.equalsIgnoreCase("pl-PL"))
		{
			returnValue = Gather.Language.PL_PL;
		}
	else if(locale.equalsIgnoreCase("pt-BR"))
		{
			returnValue = Gather.Language.PT_BR;
		}
	else if(locale.equalsIgnoreCase("pt-PT"))
		{
			returnValue = Gather.Language.PT_PT;
		}
	else if(locale.equalsIgnoreCase("ru-RU"))
		{
			returnValue = Gather.Language.RU_RU;
		}
	else if(locale.equalsIgnoreCase("sv-SE"))
		{
			returnValue = Gather.Language.SV_SE;
		}
	else if(locale.equalsIgnoreCase("zh-CN"))
		{
			returnValue = Gather.Language.ZH;
		}
	else if(locale.equalsIgnoreCase("zh-HK"))
		{
			returnValue = Gather.Language.ZH;
		}
	else if(locale.equalsIgnoreCase("zh-TW"))
		{
			returnValue = Gather.Language.ZH_TW;
		}
		else 
		{
			returnValue = Gather.Language.EN_US;
		}
	return returnValue;
}



}
