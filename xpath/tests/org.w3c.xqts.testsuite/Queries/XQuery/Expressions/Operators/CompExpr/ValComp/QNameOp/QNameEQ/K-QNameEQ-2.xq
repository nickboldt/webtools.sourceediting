(:*******************************************************:)
(: Test: K-QNameEQ-2                                     :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `QName("example.com/", "p:ncname") eq QName("example.com/", "pdifferent:ncname")`. :)
(:*******************************************************:)
QName("example.com/", "p:ncname") eq
			       QName("example.com/", "pdifferent:ncname")