import shajs from 'sha.js';

type Algorithm = 'sha' | 'sha1' | 'sha224' | 'sha256' | 'sha384' | 'sha512';

export function shaEncrypt(str: string, encryption: Algorithm): string {
  return shajs(encryption).update(str).digest('hex');
}
