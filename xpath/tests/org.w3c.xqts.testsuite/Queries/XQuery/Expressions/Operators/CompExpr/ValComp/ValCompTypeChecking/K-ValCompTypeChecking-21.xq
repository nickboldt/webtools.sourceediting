(:*******************************************************:)
(: Test: K-ValCompTypeChecking-21                        :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Value comparison involving xs:untypedAtomic, which leads to an inexistent operator mapping. :)
(:*******************************************************:)
xs:float(0) lt xs:untypedAtomic("1")