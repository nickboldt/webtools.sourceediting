(:*******************************************************:)
(: Test: K-SeqExprCast-193                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of casting a negative xs:yearMonthDuration to xs:dayTimeDuration. :)
(:*******************************************************:)

			xs:string(xs:dayTimeDuration(xs:yearMonthDuration("-P543Y456M")))
			eq "PT0S"