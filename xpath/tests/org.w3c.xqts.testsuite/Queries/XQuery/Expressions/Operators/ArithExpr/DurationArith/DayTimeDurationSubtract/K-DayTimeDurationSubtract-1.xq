(:*******************************************************:)
(: Test: K-DayTimeDurationSubtract-1                     :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Simple test of substraction an xs:dayTimeDuration with PT0S. :)
(:*******************************************************:)
xs:dayTimeDuration("P3DT4H3M3.100S") - xs:dayTimeDuration("PT0S")
			   eq xs:dayTimeDuration("P3DT4H3M3.100S")