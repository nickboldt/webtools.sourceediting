(:*******************************************************:)
(: Test: K-TimeEQ-17                                     :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The operator 'le' is not available between xs:dateTime and xs:date . :)
(:*******************************************************:)
xs:date("1999-12-04") le
				       xs:time("12:12:23")