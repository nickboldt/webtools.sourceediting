(:*******************************************************:)
(: Test: K-SeqBooleanFunc-8                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Extracting EBV from xs:anyURI is allowed.    :)
(:*******************************************************:)
not(boolean(xs:anyURI("")))