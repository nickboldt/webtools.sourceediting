(:*******************************************************:)
(: Test: K-AnyURIEqual-10                                :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: A test whose essence is: `xs:untypedAtomic("example.com/") ne xs:anyURI("example.com/No")`. :)
(:*******************************************************:)
xs:untypedAtomic("example.com/") ne xs:anyURI("example.com/No")