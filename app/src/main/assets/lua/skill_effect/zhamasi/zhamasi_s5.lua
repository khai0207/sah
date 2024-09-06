--v3
--[[
zhamasi
1
0
siwangbaoxue
1
{default,gongji6,1,0,0,88,1.000000}
0

0

0

3
{0,zhamasi,4,0,0.000000,0.000000,88,0}
1
{default,gong6_texiao1,1,0,0,88,1.000000}
0

0

0

{0,zhamasi,3,0,0.000000,0.000000,1,0}
1
{default,gongji6_texiao1_2,0,0,0,1,1.000000}
0

0

0

{0,andy,0,0,0.000000,0.000000,0,0}
0

0

0

0


daiji
]]--
local skillTest = {
attack_type       = "yuangong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"gongji6",1.000000,0,88,1}}, 
pos_sequence      = {}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"zhamasi",4,0,0.000000,0.000000,{{"gong6_texiao1",1.000000,0,88,1}},{},{},{},0},
{"zhamasi",3,0,0.000000,0.000000,{{"gongji6_texiao1_2",1.000000,0,1,0}},{},{},{},0},
{"andy",0,0,0.000000,0.000000,{},{},{},{},0}}, 
bloodNum          = 10,
flyAnim           = "ladeng",
flyAnim_equence   = {{"impact",1}}, 
par               = "",
fly_v             = 100,
fly_h             = 0,
hurtAnim          = "siwangbaoxue",
hurtAnim_equence  = {{"impact",1}},
skillCallFunc     = function(self,battleSkill,battleTable) 
   local skillData = battleTable.m_currentFrameData; 
   table.foreach(skillData,function(k,v) 
   end); 
   local hpValue = 1;
   local function attackEnd()
   end
   battleSkill:createNormalAttack({skills_table = self,hpValue = skillData.hurt,animEndCallFunc = attackEnd});
end, 
}
return skillTest
